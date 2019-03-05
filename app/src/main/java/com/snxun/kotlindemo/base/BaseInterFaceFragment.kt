package com.snxun.kotlindemo.base

import android.content.Context
import com.lodz.android.pandora.base.fragment.BaseFragment
import com.snxun.kotlindemo.manager.FunctionManager

/**
 * Created by liancl on 2019/3/4.
 */
abstract class BaseInterFaceFragment : BaseFragment() {


    public lateinit var functionManager: FunctionManager

    fun setFunctionManger(functionManager: FunctionManager){
        this.functionManager=functionManager
    }

    open  fun getNpnrFragTag():String {return ""}

    open  fun getWpnrFragTag():String {return ""}

    open  fun getNpwrFragTag():String {return ""}

    open  fun getWpwrFragTag():String {return ""}
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BaseInterActivity){
            context.toImplementsInterface(tag!!,getNpnrFragTag(),getNpwrFragTag(),getWpwrFragTag(),getWpnrFragTag())
        }
    }


}