package com.snxun.kotlindemo.test.design.watch

import com.snxun.kotlindemo.test.design.watch.impl.WorkerObserver

/**
 * Created by liancl on 2019/8/6.
 *
 */
abstract class Worker : WorkerObserver {


    override fun notifyWork() {

    }

    lateinit var name :String
}