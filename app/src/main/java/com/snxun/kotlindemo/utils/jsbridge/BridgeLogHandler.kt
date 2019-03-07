package com.snxun.kotlindemo.utils.jsbridge

import android.text.TextUtils
import com.lodz.android.corekt.log.PrintLog
import com.snxun.kotlindemo.constant.AppConstant

/**
 * Created by zhouL on 2018/11/23.
 */
abstract class BridgeLogHandler
/**
 * 构造函数
 * @param apiName 接口名
 */
    (val apiName: String) : BridgeHandler {

    override fun handler(data: String, function: CallBackFunction) {
        PrintLog.vS(
            AppConstant.TAG,
            "js call android [" + apiName + "]  <---  " + if (TextUtils.isEmpty(data)) "null" else data
        )
        onHandler(data, function)
    }

    abstract fun onHandler(data: String, function: CallBackFunction)
}
