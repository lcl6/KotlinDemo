package com.snxun.kotlindemo.test.design.factory.methor

/**
 * 工厂方法 复杂的程序从简单工厂中分离出来
 *
 * Created by liancl on 2019/8/5.
 *
 */
fun main(args: Array<String>) {

    val benzFactory = BenzFactory()
    val car = benzFactory.getCar()
    println(car.name())
    val bmwFactory = BmwFactory()
    val car1 = bmwFactory.getCar()
    println(car1.name())


}