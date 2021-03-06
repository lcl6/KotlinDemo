package com.snxun.kotlindemo.test.design.build.person

/**
 * 将一个复杂对象的构建和他的表示分离 使得同样的构建过程可以创建不同的表示
 *
 * Created by liancl on 2019/7/31.
 */
class FatPerson : PersonConfig() {
    var person: Person =
        Person()

    override fun buildHand() {
        person.hand="2"
    }

    override fun bulidFoot() {
        person.foot="2"
    }

    override fun buildHead() {
        person.head="2"
    }

    override fun buildBody() {
        person.body="2"
    }
}