package com.snxun.kotlindemo

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by liancl on 2019/2/13.
 *
 */
 class MainActivity : AppCompatActivity(){
    var mListener: ((String) -> Unit)? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_btn).setOnClickListener {

            mListener?.invoke("你好")
        }

        setOnMyClick { mag ->
            Toast.makeText(this,mag,Toast.LENGTH_LONG).show()
        }
    }

    fun setOnMyClick(listener: (mag:String)->Unit){
        mListener=listener
    }


}

