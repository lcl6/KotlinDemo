package com.snxun.kotlindemo.test.design.watch

import com.snxun.kotlindemo.test.design.watch.impl.SecretarySubject

/**
 * 秘书类
 * Created by liancl on 2019/8/6.
 *
 */
class Secretary:SecretarySubject{

    private lateinit var list: ArrayList<Worker>

    override fun addWorker(workList: ArrayList<Worker>) {
        this.list=workList
    }

    override fun notifyWork() {

        list.forEach {
            it.notifyWork()
        }
    }
}