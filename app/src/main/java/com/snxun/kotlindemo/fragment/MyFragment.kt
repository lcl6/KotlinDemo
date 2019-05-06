package com.snxun.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.base.BaseInterFaceFragment
import kotlinx.android.synthetic.main.fg_my.*

/**
 * Created by liancl on 2019/3/1.
 */
class MyFragment : BaseInterFaceFragment() {

    companion object {
        val TAG_MyFragment : String="cccc"

        fun  instance():MyFragment{
            val myFragment = MyFragment()
            return myFragment
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fg_my
    }

    override fun findViews(view: View, savedInstanceState: Bundle?) {
        super.findViews(view, savedInstanceState)

        ButterKnife.bind(this,view)
        showStatusCompleted()

    }

    override fun initData(view: View) {
        super.initData(view)
    }

    override fun getNpnrFragTag(): String {
        return TAG_MyFragment
    }

    override fun getWpwrFragTag(): String {
        return TAG_MyFragment
    }




    override fun setListeners(view: View) {
        super.setListeners(view)

        tv_click_my.setOnClickListener {
            functionManager.invokeFunction(TAG_MyFragment)
        }

        tv_click_my2.setOnClickListener {
            functionManager.invokeFunction(TAG_MyFragment,"bbbb",String::class.java)
        }

    }




}