package com.snxun.kotlindemo.utils.jsbridge

class DefaultHandler : BridgeHandler {

    internal var TAG = "DefaultHandler"

    override fun handler(data: String, function: CallBackFunction) {
        function?.onCallBack("DefaultHandler response data")
    }

}
