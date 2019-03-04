package com.snxun.kotlindemo.bean.func

/**
 * Created by liancl on 2019/3/1.
 */
open class Function {

    lateinit var functionName: String


    constructor()

    constructor( functionName: String){
        this.functionName=functionName
    }

}