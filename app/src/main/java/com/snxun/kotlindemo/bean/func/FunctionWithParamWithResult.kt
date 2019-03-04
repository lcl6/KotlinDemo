package com.snxun.kotlindemo.bean.func

/**
 * 有参数有返回值
 * Created by liancl on 2019/3/1.
 */
abstract class FunctionWithParamWithResult<Result,Param>:Function {

   constructor()

   constructor(functionName :String){
      super.functionName=functionName
   }

   abstract fun  function(param: Param):Result
}