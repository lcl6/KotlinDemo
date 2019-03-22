package com.snxun.kotlindemo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.lodz.android.pandora.widget.rv.recycler.BaseRecyclerViewAdapter
import com.snxun.kotlindemo.R

/**
 * Created by liancl on 2019/3/21.
 *
 */
class NewsAdapter(context: Context) : BaseRecyclerViewAdapter<String>(context){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Viewholder(getLayoutView(parent, R.layout.item_news))
    }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {


    }


    class Viewholder : RecyclerView.ViewHolder {



        constructor(itemView: View) : super(itemView){
            ButterKnife.bind(this,itemView)
        }


    }

}