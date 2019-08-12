package com.snxun.kotlindemo.test.design.adapter

import com.snxun.kotlindemo.test.design.adapter.impl.UsbImpl

/**
 * Created by liancl on 2019/8/9.
 *
 */
open class Usb :UsbImpl {
    override fun isusb() {
        println("这是usb 接口")
    }
}