package com.snxun.kotlindemo.utils.notch

import android.app.Activity
import android.graphics.Rect
import android.view.WindowManager
import com.snxun.kotlindemo.utils.notch.impl.INotchImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchRectImpl
import java.util.*

/**
 * 适配华为
 * Created by liancl on 2019/7/30.
 *
 */
class NotchHw :INotchImpl{

    /*** 刘海屏全屏显示FLAG */
    val FLAG_NOTCH_SUPPORT = 0x00010000

    override fun hasNotch(ac: Activity): Boolean {
        var ret = false
        try {
            val cl = ac.classLoader
            val hwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = hwNotchSizeUtil.getMethod("hasNotchInScreen")
            ret = get.invoke(hwNotchSizeUtil) as Boolean
        } catch (ignore: Throwable) {
            ignore.printStackTrace()
        }
        return ret
    }

    override fun displayInNotch(ac: Activity) {
        try {
            val window = ac.window
            val layoutParams = window.attributes
            val layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx")
            val con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams::class.java)
            val layoutParamsExObj = con.newInstance(layoutParams)
            val method = layoutParamsExCls.getMethod("addHwFlags", Int::class.javaPrimitiveType!!)
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT)
            window.windowManager.updateViewLayout(window.decorView, window.decorView.layoutParams)
        } catch (ignore: Throwable) {
            ignore.printStackTrace()
        }

    }


    /**
     * 设置华为刘海屏手机不使用刘海区
     */
    fun setNotDisplayInNotch(activity: Activity) {
        try {
            val window = activity.window
            val layoutParams = window.attributes
            val layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx")
            val con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams::class.java)
            val layoutParamsExObj = con.newInstance(layoutParams)
            val method = layoutParamsExCls.getMethod("clearHwFlags", Int::class.javaPrimitiveType!!)
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT)
            window.windowManager.updateViewLayout(window.decorView, window.decorView.layoutParams)
        } catch (ignore: Throwable) {
        }

    }


    override fun getNotchRect(ac: Activity, backRect: NotchRectImpl) {
        try {
            val cl = ac.getClassLoader()
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("getNotchSize")
            val ret = get.invoke(HwNotchSizeUtil) as IntArray
            val rects = ArrayList<Rect>()
            rects.add(ScreenUtil.calculateNotchRect(ac, ret[0], ret[1]))
            backRect.onNotchRect(rects)
        } catch (ignore: Throwable) {
            backRect.onNotchRect(null)
        }


    }
}