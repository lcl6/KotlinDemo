package com.snxun.kotlindemo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.adapter.TestRvBinderAdapter
import com.snxun.kotlindemo.adapter.binder.MultiNewsBinder
import com.snxun.kotlindemo.adapter.binder.NormalNewsBinder
import kotlinx.android.synthetic.main.ac_rv_binder.*

/**
 * Created by liancl on 2019/7/31.
 *
 */
class RecyclerBinderActivity : BaseActivity() {

    companion object{
        val  TYPE_NOMARL :Int= 1
        val  TYPE_MULTI :Int=2

        fun start(context:Context){
            val intent = Intent()
            intent.setClass(context,RecyclerBinderActivity::class.java)
            context.startActivity(intent)

        }

    }


    override fun getLayoutId(): Int {
        return R.layout.ac_rv_binder
    }


    override fun initData() {
        super.initData()
        showStatusCompleted()
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        initRecycler()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycler() {

        val normalList = ArrayList<String>()

        for (i in 0..3){
            normalList.add("")
        }

        val multiList = ArrayList<String>()
        for (i in 0..3){
            multiList.add("")
        }
        rv_binder.layoutManager=LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        val binderAdapter = TestRvBinderAdapter(getContext())
        rv_binder.adapter= binderAdapter

        val nomarlBinder = NormalNewsBinder(getContext(), TYPE_NOMARL)
        nomarlBinder.setData(normalList)
        binderAdapter.addBinder(nomarlBinder)

        val multiBinder = MultiNewsBinder(getContext(), TYPE_MULTI)
        multiBinder.setData(multiList)
        binderAdapter.addBinder(multiBinder)

    }

}