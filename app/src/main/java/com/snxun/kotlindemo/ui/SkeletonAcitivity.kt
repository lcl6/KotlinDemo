package com.snxun.kotlindemo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.skeletonlibrary.Skeleton
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.adapter.NewsAdapter

/**
 * Created by liancl on 2019/3/21.
 *
 */
class SkeletonAcitivity : BaseActivity() {

    @BindView(R.id.recycler_view)
    lateinit var recycler: RecyclerView


    companion object {
        fun  start(context: Context){
            val intent = Intent()
            intent.setClass(context,SkeletonAcitivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.ac_skeleton
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        ButterKnife.bind(this)
        showStatusCompleted()
        initRecycelrView()

    }

    @SuppressLint("WrongConstant")
    private fun initRecycelrView() {


        val mList = ArrayList<String>()
        for (  i  in 1 ..10){
            mList.add("")
        }

        val linearLayoutManager = LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager=linearLayoutManager
        val newsAdapter = NewsAdapter(getContext())
        recycler.adapter=newsAdapter
        newsAdapter.setData(mList)

        val skeletonScreen = Skeleton.bind(recycler)
            .adapter(newsAdapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.item_skeleton_news)
            .show()

        recycler.postDelayed({ skeletonScreen.hide() }, 3000)
    }

    override fun initData() {
        super.initData()

    }

    override fun onClickBackBtn() {
        super.onClickBackBtn()
        finish()
    }

}