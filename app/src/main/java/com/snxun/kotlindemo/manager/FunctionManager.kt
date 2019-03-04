package com.snxun.kotlindemo.manager

import android.text.TextUtils
import com.snxun.kotlindemo.bean.func.FunctionNoParamNoResult
import com.snxun.kotlindemo.bean.func.FunctionNoParamWithResult
import com.snxun.kotlindemo.bean.func.FunctionWithParamNoResult
import com.snxun.kotlindemo.bean.func.FunctionWithParamWithResult

/**
 * 接口管理
 * Created by liancl on 2019/3/1.
 */
class FunctionManager {






    companion object {
        val functionManager : FunctionManager? = null
        var mFunctionWithParamWithResultMap: HashMap<String, FunctionWithParamWithResult<Any,Any>> = HashMap()
        var mFunctionWithParamNoResultMap:  HashMap<String, FunctionWithParamNoResult<Any>> = HashMap()
        var mFunctionNoParamWithResultMap = HashMap<String, FunctionNoParamWithResult<Any>>()
        var mFunctionNoParamNoResultMap:  HashMap<String, FunctionNoParamNoResult> = HashMap()


        fun  getInstance():FunctionManager{
            if(functionManager==null){
                return  FunctionManager()
            }
    return functionManager
}
}


    /*
     *添加无参数无返回
     */
    fun  addFunction(functionNoParamNoResult: FunctionNoParamNoResult):FunctionManager{
        mFunctionNoParamNoResultMap!![functionNoParamNoResult!!.functionName] = functionNoParamNoResult
        return this
    }

    /*
     *添加有参数有返回
     */
    fun  addFunction(functionWithParamWithResult: FunctionWithParamWithResult<Any,Any>):FunctionManager{
        mFunctionWithParamWithResultMap!![functionWithParamWithResult!!.functionName] = functionWithParamWithResult
        return this
    }
    /*
     *添加有参数无返回
     */
    fun  addFunction(functionWithParamNoResult: FunctionWithParamNoResult<Any>):FunctionManager{
        mFunctionWithParamNoResultMap!![functionWithParamNoResult!!.functionName] = functionWithParamNoResult
        return this
    }


    /*
     *添加无参数有返回
     */
    fun  addFunction(functionNoParamWithResult: FunctionNoParamWithResult<Any>):FunctionManager{
        mFunctionNoParamWithResultMap!![functionNoParamWithResult!!.functionName] = functionNoParamWithResult
        return this
    }


    /**
     * 执行没有参数没有返回
     */
    fun invokeFunction(key :String){

        val functionNoParamNoResult = mFunctionNoParamNoResultMap[key]
        functionNoParamNoResult?.function()?:try {
            throw Exception("function not found")
        }catch (e:Exception ){
           e.printStackTrace()
        }
    }

    fun <Result,Param> invokeFunction( key:String,p:Param,result: Class<Result>): Result? {
            if(TextUtils.isEmpty(key)){
                return null
            }
            var f= mFunctionWithParamWithResultMap[key]

            if(f!=null){
                if(result==null){
                    return f?.function(p!!) as Result?
                }else{
                    return result.cast(f?.function(p!!))
                }
            }else{
                try {
                    throw  Exception("function not found")
                } catch ( e :Exception) {
                    e.printStackTrace()
                }
            }
        return null
    }



}