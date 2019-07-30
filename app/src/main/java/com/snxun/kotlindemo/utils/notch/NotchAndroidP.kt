package com.snxun.kotlindemo.utils.notch

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.snxun.kotlindemo.utils.notch.impl.INotchImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchRectImpl

/**
 * 、借鉴
 * 适配android p
 * Created by liancl on 2019/7/30.
 */
class NotchAndroidP : INotchImpl {


    /**android p 直接返回*/
    override fun hasNotch(ac: Activity): Boolean {
        return true
    }

    @RequiresApi(Build.VERSION_CODES.P)
    /**绘制到耳朵区***/
    override fun displayInNotch(ac: Activity) {
        val window = ac.window
        // 延伸显示区域到耳朵区
        val lp = window.attributes
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = lp
        // 允许内容绘制到耳朵区
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(Build.VERSION_CODES.M)
    /***获取耳朵***/
    override fun getNotchRect(ac: Activity, notchRectImpl: NotchRectImpl) {
        val contentView = ac.window.decorView
        contentView.post(Runnable {
            val windowInsets = contentView.rootWindowInsets
            if (windowInsets != null) {
                val cutout = windowInsets.displayCutout
                if (cutout != null) {
                    val rects = cutout.boundingRects
                    notchRectImpl.onNotchRect(rects)
                    return@Runnable
                }
            }
            notchRectImpl.onNotchRect(null)
        })
    }
}