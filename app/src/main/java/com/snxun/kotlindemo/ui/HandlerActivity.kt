package com.snxun.kotlindemo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.constant.AppConstant
import kotlinx.android.synthetic.main.ac_handler.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 协程 的使用
 * Created by liancl on 2019/4/11.
 *
 */
class HandlerActivity : BaseActivity() {




    companion object {

        fun start(context: Context){
            val intent = Intent()
            intent.setClass(context,HandlerActivity::class.java)
            context.startActivity(intent)

        }
    }
    override fun getLayoutId(): Int {
        return R.layout.ac_handler
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        showStatusCompleted()
    }

    override fun initData() {
        super.initData()
    }

    @SuppressLint("SetTextI18n")
    override fun setListeners() {
        super.setListeners()

        tv_click.setOnClickListener {

            //Dispatchers.Main  本质还是在主线程操作  进行大规模数据操作会anr
//            GlobalScope.launch(Dispatchers.Main){
//
//                for ( i in 1..10){
//                    delay(1000)
//                    tv_name.text="倒计时 $i"
//                }
//            }


            //withContext 帮助我们在协程上下文环境中切换线程 并返回执行结果
            GlobalScope.launch (Dispatchers.Main){


                val reusult = withContext(Dispatchers.IO) {
                    delay(1000)
                    1000
                }
                print(reusult)
                Log.e(AppConstant.TAG, reusult.toString())
                tv_name.text="返回值是：$reusult"
            }
        }



        tv_click_time.setOnClickListener {
//            runBlocking <Unit>{
//                val measureTimeMillis = measureTimeMillis {
//                    var one = doSomeThingOne()
//                    var two = doSomeThingTwo()
//                    var time = one + two
//                    tv_time.text = "数量：$time"
//                }
//
//
//            }

            //并行
            runBlocking <Unit>{
                val measureTimeMillis = measureTimeMillis {
                    var one = async { doSomeThingOne() }
                    var two =async{ doSomeThingTwo()}
                    var time = one.await() + two.await()
                    tv_time.text = "数量：$time"
                }

            }

        }












    }

    //当调用suspend 修饰时 会发生协程挂起
    private suspend fun doSomeThingTwo() :Int{
        for (i in 11..20){
            delay(1000)
            Log.e(AppConstant.TAG,"$i")
        }

        return 10

    }

    private suspend fun doSomeThingOne() :Int{
        for (i in 1..10){
            delay(1000)
            Log.e(AppConstant.TAG,"$i")
        }
        return 15

    }

    override fun onClickBackBtn() {
        super.onClickBackBtn()
        finish()
    }

}