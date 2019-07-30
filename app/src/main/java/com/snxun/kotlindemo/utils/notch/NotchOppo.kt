package com.snxun.kotlindemo.utils.notch

import android.app.Activity
import android.graphics.Rect
import android.text.TextUtils
import com.snxun.kotlindemo.utils.notch.impl.INotchImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchRectImpl
import java.util.*

/**
 * Created by liancl on 2019/7/30.
 *
 */
class NotchOppo :INotchImpl {
    override fun hasNotch(ac: Activity): Boolean {
        var ret = false
        try {
            ret = ac.packageManager.hasSystemFeature("com.oppo.feature.screen.heteromorphism")
        } catch (ignore: Throwable) {
            ignore.printStackTrace()
        }
        return ret
    }

    override fun displayInNotch(ac: Activity) {


    }

    override fun getNotchRect(ac: Activity, backRect: NotchRectImpl) {

        try {
            val screenValue = getScreenValue()
            if (!TextUtils.isEmpty(screenValue)) {
                val split = screenValue.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val leftTopPoint = split[0]
                val leftAndTop = leftTopPoint.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val rightBottomPoint = split[1]
                val rightAndBottom =
                    rightBottomPoint.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val left: Int
                val top: Int
                val right: Int
                val bottom: Int
                if (ScreenUtil.isPortrait(ac)) {
                    left = Integer.valueOf(leftAndTop[0])
                    top = Integer.valueOf(leftAndTop[1])
                    right = Integer.valueOf(rightAndBottom[0])
                    bottom = Integer.valueOf(rightAndBottom[1])
                } else {
                    left = Integer.valueOf(leftAndTop[1])
                    top = Integer.valueOf(leftAndTop[0])
                    right = Integer.valueOf(rightAndBottom[1])
                    bottom = Integer.valueOf(rightAndBottom[0])
                }
                val rect = Rect(left, top, right, bottom)
                val rects = ArrayList<Rect>()
                rects.add(rect)
                backRect.onNotchRect(rects)
            }
        } catch (ignore: Throwable) {
            backRect.onNotchRect(null)
            ignore.printStackTrace()
        }
    }
    /**
     * 获取刘海的坐标
     * 属性形如：[ro.oppo.screen.heteromorphism]: [378,0:702,80]
     * 获取到的值为378,0:702,80
     * (378,0)是刘海区域左上角的坐标
     * (702,80)是刘海区域右下角的坐标
     */
    private fun getScreenValue(): String {
        var value = ""
        val cls: Class<*>
        try {
            cls = Class.forName("android.os.SystemProperties")
            val get = cls.getMethod("get", String::class.java)
            val `object` = cls.newInstance()
            value = get.invoke(`object`, "ro.oppo.screen.heteromorphism") as String
        } catch (ignore: Throwable) {
        }

        return value
    }
}