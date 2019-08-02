package com.snxun.kotlindemo.test.design.single.kotlin

/**
 * 静态内部内的实例 推荐
 * Created by liancl on 2019/8/2.
 *
 */
class SingleLazyInKt private constructor() {


    companion object{
        var  singleLazyInKt=SingleLazyInFoKt.instance
    }

    private object SingleLazyInFoKt{
        var instance=SingleLazyInKt()
    }

}