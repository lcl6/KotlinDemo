package com.snxun.kotlindemo.bean.func

/**
 * 有参数无返回值
 * Created by liancl on 2019/3/1.
 */
abstract class FunctionWithParamNoResult<Param>(name:String) :Function(name){

    abstract fun function(par:Param)
}