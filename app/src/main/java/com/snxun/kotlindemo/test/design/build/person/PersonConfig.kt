package com.snxun.kotlindemo.test.design.build.person

/**
 * Created by liancl on 2019/7/31.
 *
 */
abstract class PersonConfig {
  abstract  fun buildHand()
  abstract fun  bulidFoot()
  abstract fun buildHead()
  abstract fun buildBody()

  fun creat(){
    buildHand()
    bulidFoot()
    buildHead()
    buildBody()
  }
}