package com.snxun.kotlindemo.bean

/**
 * Created by liancl on 2019/2/11.
 *
 */

data class Person(val a:Int){
     val age:Int
        get() {
            return 2+a
        }
}

