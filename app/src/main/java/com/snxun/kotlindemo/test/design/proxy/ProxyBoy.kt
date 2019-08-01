package com.snxun.kotlindemo.test.design.proxy

/**
 * Created by liancl on 2019/8/1.
 *
 */
class ProxyBoy(var girl: Girl,var  boy: Boy) :ProxyBehavier {

    public  var name:String=""
    public  var value:String=""


    override fun sendFlovir(flow:String) {
        boy.sendFlovir(flow)
        girl.value+=flow

    }

    override fun sendGif(gif:String) {
        boy.sendGif(gif)
        girl.value+=gif
    }

    override fun inviteMeet(meet:String) {
        boy.inviteMeet(meet)
        girl.value+=meet
    }


    override fun play(play:String) {
        boy.play(play)
        girl.value+=play
    }



}