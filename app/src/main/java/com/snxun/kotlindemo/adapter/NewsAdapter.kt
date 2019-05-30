package com.snxun.kotlindemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.lodz.android.pandora.widget.rv.recycler.BaseRecyclerViewAdapter
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.bean.NewsListBean
import com.snxun.kotlindemo.utils.jsbridge.dif.DiffItemCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*

/**
 * Created by liancl on 2019/3/21.
 *
 */
class NewsAdapter(context: Context) : BaseRecyclerViewAdapter<NewsListBean>(context) {

    var diffItemCallback = DiffItemCallback()
    var mTextDiffl= AsyncListDiffer<NewsListBean>(this, diffItemCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Viewholder(getLayoutView(parent, R.layout.item_news))
    }


    public fun submitList(data:List<NewsListBean> ){
        mTextDiffl.submitList(data)
    }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        setItem(holder as Viewholder,getItem(position) ,holder.position)
    }

    override fun getItem(position: Int): NewsListBean? {
        return mTextDiffl.currentList[position]

    }

    override fun getItemCount(): Int {
        return mTextDiffl.currentList.size
    }

    @SuppressLint("SetTextI18n")
    private fun setItem(viewholder: Viewholder, item: NewsListBean?, position: Int) {
        if (item != null) {
            viewholder.tv_title_id.text = "${item.desc} --GoogleAndroid8.0-$position"
        }
        viewholder.tv_title_id.setOnClickListener {
            liset.intemClick(item)

        }
    }


    private lateinit var liset: Listener

    fun setListener(liset :Listener ){
        this.liset=liset
    }
    interface Listener{
        fun intemClick(item: NewsListBean?)
    }

    class Viewholder : RecyclerView.ViewHolder,LayoutContainer {
        override val containerView: View

        constructor(containerView: View) : super(containerView) {
            this.containerView = containerView
        }
    }
}