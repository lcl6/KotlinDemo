package com.snxun.kotlindemo.test.design.factory.methor

import com.snxun.kotlindemo.test.design.factory.simple.CarName

/**
 * Created by liancl on 2019/8/5.
 *
 */
interface CarFactory {

    fun getCar() : CarName

}