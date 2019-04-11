package com.snxun.kotlindemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.ui.MotionActivity
import com.snxun.kotlindemo.ui.PageWebviewActivity
import com.snxun.kotlindemo.ui.SkeletonAcitivity
import com.snxun.kotlindemo.ui.ViewPageActivity

/**
 * Created by liancl on 2019/2/13.
 *
 */
class MainActivity : BaseActivity(){

        @BindView(R.id.tv_btn)
        lateinit var  tv : TextView
    @BindView(R.id.tv_btn_fragment)
    lateinit var  tvFragment : TextView

    @BindView(R.id.tv_btn_h5)
    lateinit var  tvWebView : TextView
    @BindView(R.id.tv_btn_zzp)
    lateinit var  tvSkeleton : TextView
    @BindView(R.id.tv_btn_motion)
    lateinit var  tvMotion : TextView


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

        ButterKnife.bind(this)
    }

    override fun setListeners() {
        super.setListeners()
        tvFragment.setOnClickListener {
            ViewPageActivity.start(getContext())
        }

        tvWebView.setOnClickListener {
            PageWebviewActivity.start(getContext())
        }

        tvSkeleton.setOnClickListener{
            SkeletonAcitivity.start(getContext())
        }
        tvMotion.setOnClickListener {
            MotionActivity.start(getContext())
        }

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

