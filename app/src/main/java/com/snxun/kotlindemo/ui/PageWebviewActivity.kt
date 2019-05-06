package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.lodz.android.corekt.log.PrintLog
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.constant.AppConstant
import com.snxun.kotlindemo.utils.jsbridge.BridgeLogHandler
import com.snxun.kotlindemo.utils.jsbridge.CallBackFunction
import kotlinx.android.synthetic.main.ac_pgw.*

/**
 * Created by liancl on 2019/3/7.
 */
class PageWebviewActivity : BaseActivity() {



    companion object {

        fun start(context: Context){
            val intent = Intent()
            intent.setClass(context,PageWebviewActivity::class.java)
            context.startActivity(intent)
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.ac_pgw
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        showStatusCompleted()
        goneTitleBar()

        web_view.initWebView()


    }

    override fun setListeners() {
        super.setListeners()
        //h5给安卓传参数 调用安卓的方法
        web_view.registerData(object :BridgeLogHandler("submitFromWeb"){
            override fun onHandler(data: String, function: CallBackFunction) {
                PrintLog.e(AppConstant.TAG,data)
                function.onCallBack("哈哈我是移动端返回的")
            }
        })
        //将数据传给h5
        web_view.sendData("pageShareMsg","你好",object : CallBackFunction {
            override fun onCallBack(data: String) {
                PrintLog.e(AppConstant.TAG,"成功了")
            }
        })


    }

    override fun initData() {
        super.initData()
        web_view.loadUrl("file:///android_asset/demo.html")

    }



}