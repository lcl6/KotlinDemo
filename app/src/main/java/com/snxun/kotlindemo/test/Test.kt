package com.snxun.kotlindemo.test

/**
 * Created by liancl on 2019/2/11.
 *
 */


fun main(args: Array<String>) {

    val button:View = Button()
    button.click()//button clicked
    button.longClick()//view longClicked

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



