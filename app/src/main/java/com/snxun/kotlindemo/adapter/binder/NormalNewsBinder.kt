package com.snxun.kotlindemo.adapter.binder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lodz.android.pandora.widget.rv.binder.RecyclerBinder
import com.snxun.kotlindemo.R

/**
 * Created by liancl on 2019/7/31.
 *
 */
class NormalNewsBinder(context: Context, binderType: Int) : RecyclerBinder<String>(context, binderType) {


    private lateinit var list: List<String>

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {

        return NormalViewholder(getLayoutView(parent, R.layout.it_normal))

    }

    override fun getData(position: Int): String? {
        return list[position]
    }

    fun setData(list:List<String>){
        this.list= list
    }

    override fun getCount(): Int {
        return list.size
    }


    class NormalViewholder(itemView : View) : RecyclerView.ViewHolder(itemView)

}