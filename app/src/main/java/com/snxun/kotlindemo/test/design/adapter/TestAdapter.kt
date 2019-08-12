package com.snxun.kotlindemo.test.design.adapter

/**
 * 适配器模式
 * 使原本接口不兼容而不能一起工作的那些类可以一起工作
 *
 * Created by liancl on 2019/8/9.
 *
 */
fun main(args: Array<String>) {

    val usbAdapter = UsbAdapter()
    usbAdapter.isps2()

}