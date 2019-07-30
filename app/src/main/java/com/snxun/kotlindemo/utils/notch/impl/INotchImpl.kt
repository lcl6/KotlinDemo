package com.snxun.kotlindemo.utils.notch.impl

import android.app.Activity

/**
 * 刘海屏接口
 * Created by liancl on 2019/7/30.
 *
 */
public  interface INotchImpl {

    fun  hasNotch( ac: Activity):Boolean

    fun displayInNotch(ac: Activity)

    fun getNotchRect(ac: Activity,backRect: NotchRectImpl)



}