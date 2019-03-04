package com.snxun.kotlindemo.base

import android.content.Context
import com.lodz.android.pandora.base.fragment.BaseFragment
import com.snxun.kotlindemo.manager.FunctionManager
import com.snxun.kotlindemo.ui.ViewPageActivity

/**
 * Created by liancl on 2019/3/4.
 */
abstract class BaseInterFaceFragment : BaseFragment() {


    public lateinit var functionManager: FunctionManager

    fun setFunctionManger(functionManager: FunctionManager){
        this.functionManager=functionManager
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ViewPageActivity){
            context.toImplementsInterface(tag!!)
        }
    }


}