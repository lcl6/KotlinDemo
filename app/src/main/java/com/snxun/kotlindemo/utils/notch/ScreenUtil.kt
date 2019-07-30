package com.snxun.kotlindemo.utils.notch

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.WindowManager

/**
 * Created by liancl on 2019/7/30.
 *
 */
object  ScreenUtil {

    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }


    /**
     * 获取contentView的高度，建议在onWindowFocusChanged之后调用，否则高度为0
     */
    fun getContentViewHeight(activity: Activity): Int {
        return getContentView(activity).height
    }

    /**
     * 获取contentView的在屏幕上的展示区域，建议在onWindowFocusChanged之后调用
     */
    fun getContentViewDisplayFrame(activity: Activity): Rect {
        val contentView = getContentView(activity)
        val displayFrame = Rect()
        contentView.getWindowVisibleDisplayFrame(displayFrame)
        return displayFrame
    }

    private fun getContentView(activity: Activity): View {
        return activity.window.decorView.findViewById(android.R.id.content)
    }


    /**
     * 获取navigationBar的高度
     */
    fun getNavigationBarHeight(context: Context): Int {
        return getDimensionPixel(context, "navigation_bar_height")
    }

    /**
     * 获取statusBar的高度
     */
    fun getStatusBarHeight(context: Context): Int {
        return getDimensionPixel(context, "status_bar_height")
    }


    private fun getDimensionPixel(context: Context, navigation_bar_height: String): Int {
        var result = 0
        val resources = context.resources
        val resourceId = resources.getIdentifier(navigation_bar_height, "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 获取屏幕宽高
     */
    private fun getScreenSize(context: Context): IntArray {
        val size = IntArray(2)
        val w = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val d = w.defaultDisplay
        val metrics = DisplayMetrics()
        d.getMetrics(metrics)
        var widthPixels = metrics.widthPixels
        var heightPixels = metrics.heightPixels
        if (Build.VERSION.SDK_INT >= 17)
            try {
                val realSize = Point()
                Display::class.java.getMethod("getRealSize", Point::class.java).invoke(d, realSize)
                widthPixels = realSize.x
                heightPixels = realSize.y
            } catch (ignored: Throwable) {
                ignored.printStackTrace()
            }

        size[0] = widthPixels
        size[1] = heightPixels
        return size
    }

    /**
     * 通过宽高计算notch的Rect
     *
     * @param notchWidth  刘海的宽
     * @param notchHeight 刘海的高
     */
    fun calculateNotchRect(context: Context, notchWidth: Int, notchHeight: Int): Rect {
        val screenSize = getScreenSize(context)
        val screenWidth = screenSize[0]
        val screenHeight = screenSize[1]
        val left: Int
        val top: Int
        val right: Int
        val bottom: Int
        if (isPortrait(context)) {
            left = (screenWidth - notchWidth) / 2
            top = 0
            right = left + notchWidth
            bottom = notchHeight
        } else {
            left = 0
            top = (screenHeight - notchWidth) / 2
            right = notchHeight
            bottom = top + notchWidth
        }
        return Rect(left, top, right, bottom)
    }
}