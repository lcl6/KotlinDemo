package com.snxun.kotlindemo.test.design.watch

/**
 * Created by liancl on 2019/8/6.
 *
 */
class LotteryWorker : Worker() {

   override fun notifyWork() {
      super.notifyWork()
      println(name+" 老板来了 我不看彩票了 ")
   }

}