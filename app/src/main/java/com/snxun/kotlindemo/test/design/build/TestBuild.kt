package com.snxun.kotlindemo.test.design.build

import com.snxun.kotlindemo.test.design.build.person.FatPerson
import com.snxun.kotlindemo.test.design.build.person.ThinPerson

/**
 * Created by liancl on 2019/7/31.
 *  将一个复杂对象的构建和他的表示分离 使得同样的构建过程可以创建不同的表示
 */
class TestBuild {

    fun main(args: Array<String>) {

            //建造者模式
          ThinPerson().creat()
          FatPerson().creat()

    }
}