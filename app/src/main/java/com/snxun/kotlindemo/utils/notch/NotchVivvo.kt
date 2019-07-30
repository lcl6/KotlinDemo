package com.snxun.kotlindemo.utils.notch

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import com.snxun.kotlindemo.utils.notch.impl.INotchImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchRectImpl
import java.util.*

/**
 * Created by liancl on 2019/7/30.
 *
 */
class NotchVivvo :INotchImpl {
    override fun hasNotch(ac: Activity): Boolean {
        var value = false
        val mask = 0x00000020
        try {
            val cls = Class.forName("android.util.FtFeature")
            val hideMethod = cls.getMethod("isFtFeatureSupport", Int::class.javaPrimitiveType!!)
            val `object` = cls.newInstance()
            value = hideMethod.invoke(`object`, mask) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return value
    }

    override fun displayInNotch(ac: Activity) {
    }

    override fun getNotchRect(ac: Activity, backRect: NotchRectImpl) {

        val rects = ArrayList<Rect>()
        val rect = ScreenUtil.calculateNotchRect(ac, getNotchWidth(ac), getNotchHeight(ac))
        rects.add(rect)
        backRect.onNotchRect(rects)
    }


    /**
     * vivo的适配文档中就告诉是27dp，未告知如何动态获取
     */
    fun getNotchHeight(context: Context): Int {
        val density = getDensity(context)
        return (27 * density).toInt()
    }

    /**
     * vivo的适配文档中就告诉是100dp，未告知如何动态获取
     */
    fun getNotchWidth(context: Context): Int {
        val density = getDensity(context)
        return (100 * density).toInt()
    }

    private fun getDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

}