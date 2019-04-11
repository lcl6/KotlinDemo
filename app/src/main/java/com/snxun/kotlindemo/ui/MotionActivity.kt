package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R

/**
 * MotionLayout 的使用
 * Created by liancl on 2019/4/11.
 *
 */
class MotionActivity : BaseActivity() {

    companion object {

        fun start(context: Context){
            val intent = Intent()
            intent.setClass(context,MotionActivity::class.java)
            context.startActivity(intent)

        }
    }
    override fun getLayoutId(): Int {
        return R.layout.ac_main_scene1
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        showStatusCompleted()
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