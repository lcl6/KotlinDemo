package com.snxun.kotlindemo.ui






import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R
import kotlinx.android.synthetic.main.ac_news_detail.*
/**
 * 新闻详情界面
 * Created by liancl on 2019/3/29.
 *
 */
class NewsDetailActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.ac_news_detail
    }

    companion object {
        val EXTEA_POSION :String="intent"

        fun start(context: Context,title:String){
            val intent = Intent()
            intent.setClass(context,NewsDetailActivity::class.java)
            intent.putExtra(EXTEA_POSION,title)
            context.startActivity(intent)
        }


    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        getTitleBarLayout().setTitleName("新闻详情")
        getIntnentData()

        showStatusCompleted()
    }

    @SuppressLint("SetTextI18n")
    private fun getIntnentData() {
        val position = intent.getStringExtra(EXTEA_POSION)
        tv_position.text= position
    }

    override fun initData() {
        super.initData()
    }

    override fun setListeners() {
        super.setListeners()
    }


    override fun onClickBackBtn() {
        super.onClickBackBtn()
        finish()
    }


}