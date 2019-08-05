package com.snxun.kotlindemo.test.design.factory.methor

import com.snxun.kotlindemo.test.design.factory.simple.BenzCar
import com.snxun.kotlindemo.test.design.factory.simple.CarName

/**
 * Created by liancl on 2019/8/5.
 *
 */
class BenzFactory :CarFactory {
    override fun getCar(): CarName {
        return BenzCar()
    }
}