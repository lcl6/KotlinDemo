package com.snxun.kotlindemo.utils.jsbridge

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Looper
import android.os.SystemClock
import android.text.TextUtils
import android.util.AttributeSet
import android.webkit.WebView
import androidx.annotation.RequiresApi
import java.util.*

@SuppressLint("SetJavaScriptEnabled")
class BridgeWebView : WebView, WebViewJavascriptBridge {

    private val TAG = "BridgeWebView"
    internal var responseCallbacks: MutableMap<String, CallBackFunction> = HashMap()
    internal var messageHandlers: MutableMap<String, BridgeHandler> = HashMap()
    internal var defaultHandler: BridgeHandler = DefaultHandler()

    private var startupMessage: MutableList<Message>? = ArrayList()

    private var uniqueId: Long = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, privateBrowsing: Boolean) : super(
        context,
        attrs,
        defStyleAttr,
        privateBrowsing
    ) {
        init()
    }

    fun getStartupMessage(): List<Message>? {
        return startupMessage
    }

    fun setStartupMessage(startupMessage: MutableList<Message>?) {
        this.startupMessage = startupMessage
    }

    /**
     *
     * @param handler
     * default handler,handle messages send by js without assigned handler name,
     * if js message has handler name, it will be handled by named handlers registered by native
     */
    fun setDefaultHandler(handler: BridgeHandler) {
        this.defaultHandler = handler
    }

    private fun init() {
        this.isVerticalScrollBarEnabled = false
        this.isHorizontalScrollBarEnabled = false
        this.settings.javaScriptEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(false)
        }
        this.webViewClient = generateBridgeWebViewClient()
    }

    protected fun generateBridgeWebViewClient(): BridgeWebViewClient {
        return BridgeWebViewClient(this)
    }

    /**
     * 获取到CallBackFunction data执行调用并且从数据集移除
     * @param url
     */
    internal fun handlerReturnData(url: String) {
        val functionName = BridgeUtil.getFunctionFromReturnUrl(url)
        val f = responseCallbacks[functionName]
        val data = BridgeUtil.getDataFromReturnUrl(url)
        if (f != null) {
            f.onCallBack(data!!)
            responseCallbacks.remove(functionName)
            return
        }
    }

    override fun send(data: String) {
        send(data, null)
    }

    override fun send(data: String, responseCallback: CallBackFunction?) {
        doSend(null, data, responseCallback)
    }


    /**
     * 保存message到消息队列
     * @param handlerName handlerName
     * @param data data
     * @param responseCallback CallBackFunction
     */
    private fun doSend(handlerName: String?, data: String, responseCallback: CallBackFunction?) {
        val m = Message()
        if (!TextUtils.isEmpty(data)) {
            m.data = data
        }
        if (responseCallback != null) {
            val callbackStr = String.format(
                BridgeUtil.CALLBACK_ID_FORMAT,
                (++uniqueId).toString() + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis())
            )
            responseCallbacks[callbackStr] = responseCallback
            m.callbackId = callbackStr
        }
        if (!TextUtils.isEmpty(handlerName)) {
            m.handlerName = handlerName
        }
        queueMessage(m)
    }

    /**
     * list<message> != null 添加到消息集合否则分发消息
     * @param m Message
    </message> */
    private fun queueMessage(m: Message) {
        if (startupMessage != null) {
            startupMessage!!.add(m)
        } else {
            dispatchMessage(m)
        }
    }

    /**
     * 分发message 必须在主线程才分发成功
     * @param m Message
     */
    internal fun dispatchMessage(m: Message) {
        var messageJson = m.toJson()
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson!!.replace("(\\\\)([^utrn])".toRegex(), "\\\\\\\\$1$2")
        messageJson = messageJson.replace("(?<=[^\\\\])(\")".toRegex(), "\\\\\"")
        val javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson)
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            this.loadUrl(javascriptCommand)
        }
    }

    /**
     * 刷新消息队列
     */
    internal fun flushMessageQueue() {
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, object : CallBackFunction {
                override fun onCallBack(data: String) {
                    // deserializeMessage 反序列化消息
                    var list: List<Message>? = null
                    try {
                        list = Message.toArrayList(data)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        return
                    }

                    if (list == null || list.size == 0) {
                        return
                    }
                    for (i in list.indices) {
                        val m = list[i]
                        val responseId = m.responseId
                        // 是否是response  CallBackFunction
                        if (!TextUtils.isEmpty(responseId)) {
                            val function = responseCallbacks[responseId]
                            val responseData = m.responseData
                            function!!.onCallBack(responseData!!)
                            responseCallbacks.remove(responseId)
                        } else {
                            var responseFunction: CallBackFunction? = null
                            // if had callbackId 如果有回调Id
                            val callbackId = m.callbackId
                            if (!TextUtils.isEmpty(callbackId)) {
                                responseFunction = object : CallBackFunction {
                                    override fun onCallBack(data: String) {
                                        val responseMsg = Message()
                                        responseMsg.responseId = callbackId
                                        responseMsg.responseData = data
                                        queueMessage(responseMsg)
                                    }
                                }
                            } else {
                                responseFunction = object : CallBackFunction {
                                    override fun onCallBack(data: String) {
                                        // do nothing
                                    }
                                }
                            }
                            // BridgeHandler执行
                            val handler: BridgeHandler?
                            if (!TextUtils.isEmpty(m.handlerName)) {
                                handler = messageHandlers[m.handlerName]
                            } else {
                                handler = defaultHandler
                            }
                            handler?.handler(m.data!!, responseFunction)
                        }
                    }
                }
            })
        }
    }


    fun loadUrl(jsUrl: String, returnCallback: CallBackFunction) {
        this.loadUrl(jsUrl)
        // 添加至 Map<String, CallBackFunction>
        responseCallbacks[BridgeUtil.parseFunctionName(jsUrl)] = returnCallback
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     * @param handlerName handlerName
     * @param handler BridgeHandler
     */
    fun registerHandler(handlerName: String, handler: BridgeHandler?) {
        if (handler != null) {
            // 添加至 Map<String, BridgeHandler>
            messageHandlers[handlerName] = handler
        }
    }

    /**
     * unregister handler
     *
     * @param handlerName
     */
    fun unregisterHandler(handlerName: String?) {
        if (handlerName != null) {
            messageHandlers.remove(handlerName)
        }
    }

    /**
     * call javascript registered handler
     * 调用javascript处理程序注册
     * @param handlerName handlerName
     * @param data data
     * @param callBack CallBackFunction
     */
    fun callHandler(handlerName: String, data: String, callBack: CallBackFunction) {
        doSend(handlerName, data, callBack)
    }

    companion object {

        val toLoadJs = "WebViewJavascriptBridge.js"
    }
}
