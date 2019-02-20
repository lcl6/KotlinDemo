package com.snxun.kotlindemo.dialog

import android.content.Context
import android.widget.TextView
import com.lodz.android.pandora.widget.dialog.BaseDialog
import com.snxun.kotlindemo.R

/**
 * Created by liancl on 2019/2/20.
 */
class CheckDialog(context: Context) : BaseDialog(context) {

    lateinit var tvMsg : TextView
    lateinit var tvNegative : TextView
    lateinit var tvPositive : TextView

    lateinit var mListener : (type:Int) -> Unit

    public val TYPE_PPOSITIVE :Int =1
    public   val TYPE_NEGATIVE :Int =2


    override fun findViews() {
        super.findViews()
        tvMsg=  findViewById(R.id.content_msg)
        tvNegative=  findViewById(R.id.negative_btn)
        tvPositive=  findViewById(R.id.positive_btn)
    }

    override fun getLayoutId(): Int {
        return R.layout.dg_check_layout
    }



    override fun initData() {
        super.initData()


    }

    fun setContentMsg(msg:String){
        tvMsg.text = msg
    }


    override fun setListeners() {
        super.setListeners()
        tvNegative.setOnClickListener { this.mListener?.invoke(TYPE_PPOSITIVE)}
        tvPositive.setOnClickListener { this.mListener?.invoke(TYPE_NEGATIVE) }
    }

    fun setListener(listener: (type:Int) -> Unit){
        mListener=listener
    }

}