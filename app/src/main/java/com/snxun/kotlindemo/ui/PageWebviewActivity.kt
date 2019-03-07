package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.widget.PgWebView

/**
 * Created by liancl on 2019/3/7.
 */
class PageWebviewActivity : BaseActivity() {

    @BindView(R.id.web_view)
    lateinit var  webView: PgWebView


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
        ButterKnife.bind(this)
        showStatusCompleted()
        goneTitleBar()

        webView.initWebView()


    }

    override fun initData() {
        super.initData()
        webView.loadUrl("http://www.baidu.com")

    }



}