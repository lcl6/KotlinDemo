package com.snxun.kotlindemo.test.design.factory.simple

/**
 * 简单工厂 提供一个创建对象实例的功能 无需关心其实现
 *
 *
 * Created by liancl on 2019/8/5.
 *
 */
fun main(args: Array<String>) {

    val creatCar = SimpleFactory.simpleFactory.CreatCar("bmsw")
    println(creatCar)


}