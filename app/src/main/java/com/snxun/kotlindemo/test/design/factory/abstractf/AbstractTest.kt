package com.snxun.kotlindemo.test.design.factory.abstractf

/**
 * 抽象工厂
 *
 * Created by liancl on 2019/8/5.
 *
 */
fun main(args: Array<String>) {

    val car = MyAbstractFactory.instance.getCar("bmsw")
    println(car.name())


}