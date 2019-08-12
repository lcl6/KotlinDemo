package com.snxun.kotlindemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.ui.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by liancl on 2019/2/13.
 *
 */
class MainActivity : BaseActivity(){




    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }
    companion object {
        fun start(context: Context){
            var intent = Intent(context,MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        getTitleBarLayout().setTitleName("motion")
    }

    override fun setListeners() {
        super.setListeners()
        tv_btn_fragment.setOnClickListener {
            ViewPageActivity.start(getContext())
        }

        tv_btn_h5.setOnClickListener {
            PageWebviewActivity.start(getContext())
        }

        tv_btn_zzp.setOnClickListener{
            SkeletonAcitivity.start(getContext())
        }
        tv_btn_motion.setOnClickListener {
            MotionActivity.start(getContext())
        }
        tv_btn_xc.setOnClickListener {
            HandlerActivity.start(getContext())
        }
        tv_btn_qmp.setOnClickListener{
            FullScreenActivity.start(getContext())
        }


        tv_btn_binder.setOnClickListener {

            RecyclerBinderActivity.start(getContext())
        }

    }


    override fun initData() {
        super.initData()
        goneTitleBar()
        showStatusCompleted()
        tv_btn.setOnClickListener {
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

