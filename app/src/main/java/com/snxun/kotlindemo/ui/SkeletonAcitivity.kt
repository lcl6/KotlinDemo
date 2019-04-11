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
import com.snxun.kotlindemo.bean.NewsListBean
import java.util.*

/**
 * Created by liancl on 2019/3/21.
 *
 */
class SkeletonAcitivity : BaseActivity() {

    @BindView(R.id.recycler_view)
    lateinit var recycler: RecyclerView

    var newsAdapter : NewsAdapter? = null
    var mList: MutableList<NewsListBean>? = null
    var mNewList: MutableList<NewsListBean>? = null

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


        mList= ArrayList()
        mNewList= ArrayList()
        for (  i  in 1 ..10){
            var bean = NewsListBean()
            bean.id= System.currentTimeMillis().toString()
            bean.desc= "aaa$i"
            (mList as ArrayList<NewsListBean>).add(bean)
        }

        val linearLayoutManager = LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager=linearLayoutManager
        newsAdapter= NewsAdapter(getContext())


//        newsAdapter!!.setData(mList as ArrayList<NewsListBean>)
        newsAdapter!!.submitList(mList as ArrayList<NewsListBean>)
        recycler.adapter=newsAdapter
        val skeletonScreen = Skeleton.bind(recycler)
            .adapter(newsAdapter!!)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(600)
            .count(10)
            .load(R.layout.item_skeleton_news)
            .show()

        recycler.postDelayed({ skeletonScreen.hide() }, 500)
    }

    override fun initData() {
        super.initData()

    }

    override fun setListeners() {
        super.setListeners()
        newsAdapter!!.setListener(object :NewsAdapter.Listener{
                override fun intemClick(item: NewsListBean?) {
                mNewList= ArrayList()
                (mNewList as ArrayList<NewsListBean>).addAll(mList!!)
                (mNewList as ArrayList<NewsListBean>).remove(item)
                mList=mNewList
                newsAdapter!!.submitList(mNewList!!)

            }

        })
//        newsAdapter!!.setOnItemClickListener(listener = { viewHolder: RecyclerView.ViewHolder, item: NewsListBean, position: Int ->
////            NewsDetailActivity.start(getContext(),item.desc)
//            mNewList= ArrayList()
////            (recycler.adapter as  NewsAdapter).let {
////                mList!!.remove(item)
////                it.setData(mList!!)
////                it.notifyDataSetChanged()
////            }
//
//            (mNewList as ArrayList<NewsListBean>).addAll(mList!!)
////            mNewList!![position].title="sdfsfas"
////            mNewList!![position].desc="ddfdfsa"
//
////            val newsListBean = NewsListBean()
////            newsListBean.desc="sfddsf"
////            newsListBean.title="sfdddddsf"
////            (this.mNewList as ArrayList<NewsListBean>).add(newsListBean)
//            (mNewList as ArrayList<NewsListBean>).remove(item)
//            newsAdapter!!.submitList(mNewList!!)
//            mList=mNewList
//
//
//        })
    }

    override fun onClickBackBtn() {
        super.onClickBackBtn()
        finish()
    }

}