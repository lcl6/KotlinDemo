package com.snxun.kotlindemo.test.design.proxy

import java.lang.reflect.Proxy




/**
 * 代理模式
 *
 * 为其他对象提供一种代理以控制对这个对象的访问
 *
 * Created by liancl on 2019/8/1.
 *
 */

    fun main(args: Array<String>) {


        //静态代理
        val girl = Girl()
        val boy= Boy()
        val proxyBoy = ProxyBoy(girl,boy)
        proxyBoy.play("出去玩吗111? ")
        proxyBoy.inviteMeet("我想约你111 ")
        proxyBoy.sendGif("送你一个礼物111 ")
        proxyBoy.sendFlovir("送你一束花11 ")
        println(girl.value)

    //动态 代理
    val girl2 = Girl()
    val newProxyInstance  = Proxy.newProxyInstance(
        boy.javaClass.classLoader,
        boy.javaClass.interfaces
    ) { proxy, method, args ->
        girl2.value+=args[0]
        method?.invoke(boy, *args)
    } as ProxyBehavier
    newProxyInstance.play("出去玩吗? ")
    newProxyInstance.inviteMeet("我想约你 ")
    newProxyInstance.sendGif("送你一个礼物 ")
    newProxyInstance.sendFlovir("送你一束花 ")
    println(girl2.value)
}
