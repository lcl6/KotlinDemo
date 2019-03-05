package com.snxun.kotlindemo.base

import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.bean.func.FunctionNoParamNoResult
import com.snxun.kotlindemo.bean.func.FunctionNoParamWithResult
import com.snxun.kotlindemo.bean.func.FunctionWithParamNoResult
import com.snxun.kotlindemo.bean.func.FunctionWithParamWithResult
import com.snxun.kotlindemo.manager.FunctionManager

/**
 * Created by liancl on 2019/3/4.
 */
abstract class BaseInterActivity :BaseActivity() {



    fun toImplementsInterface(tag : String,npnr :String ,npwr :String,wpwr :String ,wpnr :String ){
        val baseFrag = supportFragmentManager.findFragmentByTag(tag) as BaseInterFaceFragment
        val functionManager = FunctionManager.getInstance()

        baseFrag.setFunctionManger(functionManager.addFunction( object : FunctionNoParamNoResult(npnr){
            override fun function() {
                listener?.npnr(npnr)
            }
        }).addFunction(object : FunctionWithParamWithResult<Any, Any>(wpwr){
            override fun function(param: Any): String {
                listener?.wpwr(param,wpwr)
                return ""
            }
        }).addFunction(object :FunctionNoParamWithResult<Any>(npwr){
            override fun function():String{
                listener?.npwr(npwr)
                return ""
            }
        }).addFunction(object :FunctionWithParamNoResult<Any>(wpnr){
            override fun function(param:Any){
                listener?.wpnr(wpnr)
            }
        }))

    }

    public lateinit var listener: Listener

    interface Listener{
        fun npnr(npnr:String)
        fun wpnr(wpnr:String)
        fun wpwr(param :Any,wpwr:String )
        fun npwr(npwr:String)
    }





}