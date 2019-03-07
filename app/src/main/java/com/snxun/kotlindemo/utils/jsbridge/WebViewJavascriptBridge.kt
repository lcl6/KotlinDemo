package com.snxun.kotlindemo.utils.jsbridge


interface WebViewJavascriptBridge {

    fun send(data: String)
    fun send(data: String, responseCallback: CallBackFunction?)


}
