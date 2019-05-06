package com.snxun.kotlindemo.dialog

import android.content.Context
import com.lodz.android.pandora.widget.dialog.BaseDialog
import com.snxun.kotlindemo.R
import kotlinx.android.synthetic.main.dg_check_layout.*

/**
 * Created by liancl on 2019/2/20.
 */
class CheckDialog(context: Context) : BaseDialog(context) {


    lateinit var mListener : (type:Int) -> Unit

    public val TYPE_PPOSITIVE :Int =1
    public   val TYPE_NEGATIVE :Int =2


    override fun findViews() {
        super.findViews()
    }

    override fun getLayoutId(): Int {
        return R.layout.dg_check_layout
    }



    override fun initData() {
        super.initData()


    }

    fun setContentMsg(msg:String){
        content_msg.text = msg
    }


    override fun setListeners() {
        super.setListeners()
        negative_btn.setOnClickListener { this.mListener?.invoke(TYPE_PPOSITIVE)}
        positive_btn.setOnClickListener { this.mListener?.invoke(TYPE_NEGATIVE) }
    }

    fun setListener(listener: (type:Int) -> Unit){
        mListener=listener
    }

}