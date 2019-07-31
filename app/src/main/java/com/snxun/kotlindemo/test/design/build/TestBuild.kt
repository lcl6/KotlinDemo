package com.snxun.kotlindemo.test.design.build

import com.snxun.kotlindemo.test.design.build.cook.CookBuilder
import com.snxun.kotlindemo.test.design.build.person.FatPerson
import com.snxun.kotlindemo.test.design.build.person.ThinPerson

/**
 * Created by liancl on 2019/7/31.
 *  将一个复杂对象的构建和他的表示分离 使得同样的构建过程可以创建不同的表示
 *  创建者模式隐藏了复杂对象的创建过程，它把复杂对象的创建过程加以抽象，通过子类继承或者重载的方式，动态的创建具有复合属性的对象。
 */
class TestBuild {

    fun main(args: Array<String>) {

            //建造者模式
          ThinPerson().creat()
          FatPerson().creat()


        CookBuilder()
            .setApple("app")
            .setCabage("cab")
            .setEgg("egg")
            .setMeat("meat")
            .setTomato("tomo")
            .build()


    }
}