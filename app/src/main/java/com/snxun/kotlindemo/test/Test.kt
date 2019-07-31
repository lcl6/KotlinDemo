package com.snxun.kotlindemo.test

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by liancl on 2019/2/11.
 *
 */


fun main(args: Array<String>) {
//    val button:View = Button()
//    button.click()//button clicked
//    button.longClick()//view longClicked

//    println(str)
//    println("---------")
//    println(str)


    //runblockling
//    textRunBlock()
//


    //提取函数重构
//    regou()


    //coroutineScope  runBlocking 差别 runBlocking 会阻塞当前线程 coroutineScope 不会
//    maina()


    //建造者模式





}


fun textRunBlock()= runBlocking {

    launch {
        delay(1000)
        println("ddddd")
    }

    println("aaaaa")
}

fun regou()= runBlocking {
    launch {
//        down()
        delay(1000)
        println("world")
    }
    println("hello")
}

suspend fun down() {
    delay(1000)
    println("world")

}


fun maina()= runBlocking {
    launch {
        delay(1000)
        println("任务执行1秒")
    }

    coroutineScope {
        val launch = launch {
            delay(5000)
            println("任务执行5秒")
        }

        delay(1000)
        println("我又执行了1秒")//这一行会在launch 之前执行


    }

    println("看啥看 结束了")//这一行会在launch 之后执行

}

val str :String by lazy {
    println("2")
    println("1")
    "adsfasfas"

}



open class View{
    open fun click() {
        println("view clicked")
    }
}
fun View.longClick() = println("view longClicked")



open class Button: View() {
    override fun click(){
        println("button clicked")
    }
}
fun Button.longClick() = println("button longClicked")



