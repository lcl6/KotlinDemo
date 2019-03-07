package com.snxun.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.base.BaseInterFaceFragment

/**
 * Created by liancl on 2019/2/28.
 */
class MainFragment : BaseInterFaceFragment() {

    @BindView(R.id.tv_click_main)
    lateinit var clMain:TextView



    companion object {

        val TAG_MainFragment : String="bbbb"

        fun instance():MainFragment{
            val mainFragment = MainFragment()
            return mainFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fg_main
    }

    override fun initData(view: View) {
        super.initData(view)
        showStatusCompleted()
    }

    override fun findViews(view: View, savedInstanceState: Bundle?) {
        super.findViews(view, savedInstanceState)
        ButterKnife.bind(this,view)
        goneTitleBar()
    }

    override fun getWpwrFragTag(): String {
        return TAG_MainFragment
    }

    override fun setListeners(view: View) {
        super.setListeners(view)
        clMain.setOnClickListener {
            functionManager.invokeFunction(TAG_MainFragment,"aaa",String::class.java)
        }


    }
}