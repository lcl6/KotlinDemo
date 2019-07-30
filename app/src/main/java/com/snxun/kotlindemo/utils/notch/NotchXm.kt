package com.snxun.kotlindemo.utils.notch

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.Window
import com.snxun.kotlindemo.utils.notch.impl.INotchImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchRectImpl
import java.util.*

/**
 * Created by liancl on 2019/7/30.
 *
 */
class NotchXm :INotchImpl {
    override fun hasNotch(ac: Activity): Boolean {
        try {
            //可能会失效
            val getInt = Class.forName("android.os.SystemProperties")
                .getMethod("getInt", String::class.java, Int::class.javaPrimitiveType)
            val notch = getInt.invoke(null, "ro.miui.notch", 0) as Int
            return notch == 1
        } catch (ignore: Throwable) {
            ignore.printStackTrace()
        }
        return false
    }

    override fun displayInNotch(ac: Activity) {

        val flag = 0x00000100 or 0x00000200 or 0x00000400
        try {
            val method = Window::class.java.getMethod(
                "addExtraFlags",
                Int::class.javaPrimitiveType!!
            )
            method.invoke(ac.window, flag)
        } catch (ignore: Exception) {
            ignore.printStackTrace()
        }


    }

    override fun getNotchRect(ac: Activity, backRect: NotchRectImpl) {

        val rect = ScreenUtil.calculateNotchRect(ac, getNotchWidth(ac), getNotchHeight(ac))
        val rects = ArrayList<Rect>()
        rects.add(rect)
        backRect.onNotchRect(rects)
    }


    private fun getNotchHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("notch_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    private fun getNotchWidth(context: Context): Int {
        val resourceId = context.resources.getIdentifier("notch_width", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }
}