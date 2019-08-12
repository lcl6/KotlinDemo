package com.snxun.kotlindemo.test.design.adapter

import com.snxun.kotlindemo.test.design.adapter.impl.Ps2Impl

/**
 * Created by liancl on 2019/8/9.
 *
 */
class UsbAdapter : Usb(),Ps2Impl{
    override fun isps2() {
        isusb()
    }
}