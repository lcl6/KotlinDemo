package com.snxun.kotlindemo.base

import com.lodz.android.corekt.log.PrintLog
import com.lodz.android.pandora.base.application.BaseApplication
import com.snxun.kotlindemo.manager.FileManager

/**
 * Created by liancl on 2019/3/7.
 */
open class App :BaseApplication() {

    companion object {
        private var sInstance: App? = null
        fun get(): App? = sInstance
    }
    override fun onStartCreate() {
        sInstance=this
        FileManager.init()
        PrintLog.setPrint(true)
        configTitleBarLayout()
    }



    override fun onExit() {
    }

    /** 配置标题栏  */
    private fun configTitleBarLayout() {
//        getBaseLayoutConfig().getTitleBarLayoutConfig().backgroundColor(R.color.color_0099ff)
//        getBaseLayoutConfig().getTitleBarLayoutConfig().titleTextColor(R.color.white)
    }

}