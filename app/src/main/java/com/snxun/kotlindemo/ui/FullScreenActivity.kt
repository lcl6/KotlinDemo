package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.lodz.android.pandora.base.activity.BaseActivity
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

        val lp = window.attributes
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = lp
        // 设置页面全屏显示
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        // 适配Activity全屏
        val contentView = this.window.decorView
        contentView.post {
            val windowInsets = contentView.rootWindowInsets
            if (windowInsets != null) {
                val cutout = windowInsets.displayCutout
                if (cutout != null) {
                    val rects = cutout.boundingRects
                    for (rect in rects) {
                        val layoutParams = tv_bott.layoutParams as LinearLayout.LayoutParams
                        layoutParams.topMargin = rect.bottom
                        tv_bott.layoutParams = layoutParams
                    }

                }
            }
        }
    }


}