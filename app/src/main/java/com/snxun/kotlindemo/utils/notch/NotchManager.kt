package com.snxun.kotlindemo.utils.notch

import android.app.Activity
import android.graphics.Rect
import android.os.Build
import com.snxun.kotlindemo.utils.notch.impl.INotchImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchInfoImpl
import com.snxun.kotlindemo.utils.notch.impl.NotchRectImpl

/**
 * 参考 https://www.jianshu.com/p/2b8db60ba8df
 * Created by liancl on 2019/7/30.
 */
class NotchManager {

    private var notchScreen:INotchImpl

    private constructor(){
        notchScreen = getNotchScreen()
    }

    companion object {
        val instance: NotchManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NotchManager()
        }
    }


    fun setDisplayInNotch(activity: Activity): NotchManager{
        notchScreen.displayInNotch(activity)
        return this
    }

    fun getNotchInfo(activity: Activity, notchScreenCallback: NotchInfoImpl) : NotchManager{
        val notchScreenInfo = NotchInfoBean()
        if (notchScreen.hasNotch(activity)) {
            notchScreen.run {
                getNotchRect(activity, object : NotchRectImpl {
                        override fun onNotchRect(rectList: List<Rect>?) {
                            if (rectList != null && rectList.isNotEmpty()) {
                                notchScreenInfo.hasNotch = true
                                notchScreenInfo.rectList = rectList
                            }
                            notchScreenCallback.onInfo(notchScreenInfo)
                        }
                    })
            }
        } else {
            notchScreenCallback.onInfo(notchScreenInfo)
        }

        return this
    }



    private fun getNotchScreen(): INotchImpl {
        var notchScreen: INotchImpl? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            notchScreen = NotchAndroidP()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when {
                RomUtils.isHuawei() -> notchScreen = NotchHw()
                RomUtils.isOppo() -> notchScreen = NotchOppo()
                RomUtils.isVivo() -> notchScreen = NotchVivvo()
                RomUtils.isXiaomi() -> notchScreen = NotchXm()
            }
        }
        return notchScreen!!
    }


}