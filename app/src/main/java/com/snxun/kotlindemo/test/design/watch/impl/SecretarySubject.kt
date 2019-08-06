package com.snxun.kotlindemo.test.design.watch.impl

import com.snxun.kotlindemo.test.design.watch.Worker

/**
 * 通知者接口
 * Created by liancl on 2019/8/6.
 *
 */
interface SecretarySubject {
     fun  notifyWork()
    fun addWorker( workList: ArrayList<Worker>)
}