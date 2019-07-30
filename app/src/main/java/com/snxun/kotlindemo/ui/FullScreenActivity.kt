package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.utils.notch.NotchInfoBean
import com.snxun.kotlindemo.utils.notch.NotchManager
import com.snxun.kotlindemo.utils.notch.impl.NotchInfoImpl
import kotlinx.android.synthetic.main.ac_full.*


/**
 * Created by liancl on 2019/7/30.
 *
 */
class FullScreenActivity : BaseActivity() {




    companion object{
        fun start(context: Context){
            val intent = Intent()
            intent.setClass(context,FullScreenActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return com.snxun.kotlindemo.R.layout.ac_full
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun initData() {
        super.initData()
        goneTitleBar()
        initFull()


        showStatusCompleted()
    }

    override fun startCreate() {
        super.startCreate()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    @RequiresApi(Build.VERSION_CODES.P)
    private fun initFull() {

        //设置显示全面屏
        NotchManager.instance.setDisplayInNotch(this).getNotchInfo(this, object : NotchInfoImpl {
            override fun onInfo(notchInfo: NotchInfoBean) {
                if (notchInfo.hasNotch) {
                    val rectList = notchInfo.rectList
                    for (rect in rectList) {
                        val layoutParams = tv_bott.layoutParams as LinearLayout.LayoutParams
                        layoutParams.topMargin = rect.bottom
                        tv_bott.layoutParams = layoutParams
                    }
                }
            }
        })





    }

}