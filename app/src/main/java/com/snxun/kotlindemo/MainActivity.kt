package com.snxun.kotlindemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.pandora.base.activity.BaseActivity

/**
 * Created by liancl on 2019/2/13.
 *
 */
class MainActivity : BaseActivity(){

        @BindView(R.id.tv_btn)
        lateinit var  tv : TextView


    override fun getLayoutId(): Int {
       return R.layout. activity_main
    }
    companion object {
        fun start(context: Context){
            var intent = Intent(context,MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        ButterKnife.bind(this)

    }


    override fun initData() {
        super.initData()
        goneTitleBar()
        showStatusCompleted()
        tv.setOnClickListener {
            mListener?.invoke("你好")
        }

        setOnMyClick { mag ->
            Toast.makeText(this,mag,Toast.LENGTH_LONG).show()
        }

    }
    var mListener: ((String) -> Unit)? =null

    fun setOnMyClick(listener: (mag:String)->Unit){
        mListener=listener
    }





}

