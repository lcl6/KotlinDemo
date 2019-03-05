package com.snxun.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.base.BaseInterFaceFragment

/**
 * Created by liancl on 2019/3/1.
 */
class MyFragment : BaseInterFaceFragment() {

    @BindView(R.id.tv_click_my)
    lateinit var clMy :TextView

    @BindView(R.id.tv_click_my2)
    lateinit var clMy2 :TextView

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

        clMy.setOnClickListener {
            functionManager.invokeFunction(TAG_MyFragment)
        }

        clMy2.setOnClickListener {
            functionManager.invokeFunction(TAG_MyFragment,"bbbb",String::class.java)
        }

    }




}