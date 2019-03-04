package com.snxun.kotlindemo.bean.func

/**
 * 无参数 有返回值
 * Created by liancl on 2019/3/1.
 */
abstract class FunctionNoParamWithResult<Result>: Function() {
    abstract fun  function() :Result
}