package com.snxun.kotlindemo.test.design.watch

/**
 *  观察者模式
 *
 * 让多个对象同时监听某一个对象
 * 这个对象在状态发生改变时会通知所有观察者对象
 * 使他们更新
 * Created by liancl on 2019/8/6.
 *
 */
fun main(args: Array<String>) {


    val lotteryWorker = LotteryWorker()
    lotteryWorker.name="我是彩票小明"

    val watchNbaWoker = WatchNbaWoker()
    watchNbaWoker.name="我是nba小王"


    val arrayList = ArrayList<Worker>()
    arrayList.add(lotteryWorker)
    arrayList.add(watchNbaWoker)

    val boss = Boss()
    boss.name="我是老板  我来了"
    println(boss.name)

    val secretary = Secretary()
    secretary.addWorker(arrayList)
    secretary.notifyWork()

}
