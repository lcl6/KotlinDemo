package com.snxun.kotlindemo.test

/**
 * Created by liancl on 2019/2/11.
 *
 */

private var url :String?=null

fun main(args: Array<String>) {
//    print("hello")
    var a= sumaa(1, 2, 3)
//    print(a)

    //对为空进行处理
//    var age :String?=sumadd(1,2)
//    var  aaa =age?.toInt() ?:-1
//    print(aaa)

    //判断是否在范围内
//    val a2 = 100
//    val b2 = 20
//    if(a2 in 1 .. b2){
//        print("我在范围内")
//    }else{
//        print("我在不范围内")
//    }

    //测试类的使用
//  val p =  Person(2)
//    print(p.age)

    // 测试成员函数和扩展函数

//    val animal : Animal = Cat()

//    println("-------------")
//    println("成员函数：${animal.shout()}")
//    println("扩展函数：${animal.eat()}")

//    ArrayList<String>().apply {
//        add("aaaa")
//        add("aaaa")
//        add("aaaa")
//        print(message = "this = "+this)
//    }.let {
//        print(it)
//    }


    with(ArrayList<String>()){
        add("bbbb")
        add("bbbb")
        add("bbbb")
        print(this)
    }.let {
        print(it)
    }




}

fun load(url: String){

}

fun sumadd(i: Int, i1: Int): String? {
    return "1"
}

fun sumaa(i: Int, i1: Int, i2: Int) :Int{
    return i+i1+i2
}

open class Animal{
    open fun shout()= print("animal is shout")
}

class Cat : Animal(){

    override fun shout() {
        println("Cat is shout")
    }
}


fun Animal.eat()= println("animal eat something")

fun Cat.eat()= println("cat eat something ")







