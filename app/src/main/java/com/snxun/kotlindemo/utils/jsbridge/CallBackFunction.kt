package com.snxun.kotlindemo.utils.jsbridge

import java.io.Serializable

interface CallBackFunction : Serializable {

    fun onCallBack(data: String)

}
