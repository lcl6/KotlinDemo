package com.snxun.kotlindemo.widget

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.lodz.android.corekt.log.PrintLog
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.constant.AppConstant
import com.snxun.kotlindemo.manager.FileManager
import com.snxun.kotlindemo.utils.jsbridge.BridgeLogHandler
import com.snxun.kotlindemo.utils.jsbridge.BridgeWebView
import com.snxun.kotlindemo.utils.jsbridge.BridgeWebViewClient
import com.snxun.kotlindemo.utils.jsbridge.CallBackFunction

/**
 * 进度条WebView
 * Created by zhouL on 2018/11/14.
 */
class PgWebView : FrameLayout {

    /** WebView  */
    private var mWebView: BridgeWebView? = null
    /** 进度条  */
    private var mProgressBar: ProgressBar? = null

    /** 监听器  */
    private var mListener: Listener? = null
    /** 是否加载成功  */
    private var isLoadSuccess = true

    /** 是否可以回退  */
    val isCanGoBack: Boolean
        get() = mWebView != null && mWebView!!.canGoBack()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_pb_webview, this)
        mWebView = findViewById(R.id.webview)
        mProgressBar = findViewById(R.id.progress_bar)
    }

    /** 初始化WebView  */
    fun initWebView() {
        mWebView!!.webViewClient = CustomWebViewClient(mWebView!!)// 设置 WebViewClient
        mWebView!!.webChromeClient = CustomWebChromeClient()// 设置 WebChromeClient
        initWebSettings(mWebView!!.settings)
    }

    /** 初始化WebSettings  */
    private fun initWebSettings(settings: WebSettings) {
        // 默认文本编码，默认值 "UTF-8"
        settings.defaultTextEncodingName = "UTF-8"
        // 是否自动加载图片
        settings.loadsImagesAutomatically = true
        // 是否支持缩放
        settings.setSupportZoom(false)
        // 设置缓存模式
        //        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        // 开启localstorage
        settings.domStorageEnabled = true // 开启 DOM storage 功能
        settings.setAppCachePath(FileManager.cacheFolderPath)
        settings.allowFileAccess = true // 可以读取文件缓存
        settings.setAppCacheEnabled(true) //开启H5(APPCache)缓存功能
    }

    /** WebView客户端  */
    private inner class CustomWebViewClient (webView: BridgeWebView) : BridgeWebViewClient(webView) {

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            mProgressBar!!.visibility = View.VISIBLE
            PrintLog.w(TAG, "onPageStarted")
            if (mListener != null) {
                mListener!!.onPageStarted(view, url, favicon!!)
            }
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
            mProgressBar!!.visibility = View.GONE
            isLoadSuccess = false
            PrintLog.e(TAG, error.toString())
            if (mListener != null) {
                mListener!!.onReceivedError(view, request, error)
            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            PrintLog.i(TAG, "onPageFinished")
            if (!isLoadSuccess) {
                isLoadSuccess = true
                return
            }
            mProgressBar!!.visibility = View.GONE
            if (mListener != null) {
                mListener!!.onPageFinished(view, url)
            }
        }
    }

    /** WebChromeClient客户端  */
    private inner class CustomWebChromeClient : WebChromeClient() {

        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            PrintLog.d(TAG, "进度 ： $newProgress")
            mProgressBar!!.progress = newProgress
            if (mListener != null) {
                mListener!!.onProgressChanged(view, newProgress)
            }
        }
    }

    /**
     * 加载地址
     * @param url 地址
     */
    fun loadUrl(url: String) {
        if (mWebView != null) {
            mWebView!!.loadUrl(url)
        }
    }

    /** 回退  */
    fun goBack() {
        if (mWebView != null) {
            mWebView!!.goBack()
        }
    }

    /** 重载  */
    fun reload() {
        if (mWebView != null) {
            mWebView!!.reload()
        }
    }

    /** 释放资源  */
    fun release() {
        if (mWebView == null) {
            return
        }
        mWebView!!.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        mWebView!!.clearHistory()
        mWebView!!.clearCache(true)
        mWebView!!.destroy()
        mWebView = null
    }

    /**
     * 订阅H5数据
     * @param handler 回调
     */
    fun registerData(handler: BridgeLogHandler?): Boolean {
        if (mWebView == null || handler == null) {
            return false
        }
        try {
            mWebView!!.registerHandler(handler.apiName, handler)
            return true
        } catch (e: Exception) {
            e.printStackTrace()

        }

        return false
    }

    /**
     * 向H5发送数据（必须要在主线程里运行）
     * @param apiName 接口名
     * @param data 数据
     * @param callBack 回调
     */
    fun sendData(apiName: String, data: String, callBack: CallBackFunction): Boolean {
        if (mWebView == null) {
            return false
        }
        try {
            PrintLog.e(
                AppConstant.TAG,
                "android send js [" + apiName + "]  --->  " + if (TextUtils.isEmpty(data)) "null" else data
            )
            mWebView!!.callHandler(apiName, data, callBack)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    /**
     * 设置监听器
     * @param listener 监听器
     */
    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {

        /**
         * 开始加载页面
         * @param webView 控件
         * @param url 地址
         */
        fun onPageStarted(webView: WebView, url: String, favicon: Bitmap)

        /**
         * 加载进度回调
         * @param webView 控件
         * @param progress 进度
         */
        fun onProgressChanged(webView: WebView, progress: Int)

        /**
         * 加载失败
         * @param webView 控件
         * @param request 请求
         * @param error 失败结果
         */
        fun onReceivedError(webView: WebView, request: WebResourceRequest, error: WebResourceError)

        /**
         * 加载完成
         * @param webView 控件
         * @param url 地址
         */
        fun onPageFinished(webView: WebView, url: String)
    }

    companion object {

        private val TAG = "PgWebViewTag"
    }
}
