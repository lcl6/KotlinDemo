package com.snxun.kotlindemo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R

/**
 * 新闻详情界面
 * Created by liancl on 2019/3/29.
 *
 */
class NewsDetailActivity : BaseActivity() {

    @BindView(R.id.tv_position)
    lateinit var tvPosi:TextView

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
        ButterKnife.bind(this)
        getTitleBarLayout().setTitleName("新闻详情")
        getIntnentData()

        showStatusCompleted()
    }

    @SuppressLint("SetTextI18n")
    private fun getIntnentData() {
        val position = intent.getStringExtra(EXTEA_POSION)
        tvPosi.text= position
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