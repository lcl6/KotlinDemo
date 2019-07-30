package com.snxun.kotlindemo.utils.dif

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.snxun.kotlindemo.bean.NewsListBean

/**
 * Created by liancl on 2019/3/29.
 *
 */
class DiffCallback (private val oldList: List<NewsListBean>, private val newList: List<NewsListBean>) : DiffUtil.Callback() {
    /**
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等，或者重写equals方法
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].desc == newList[newItemPosition].desc
    }

    /**
     * 老数据集size
     */
    override fun getOldListSize(): Int {
        return oldList.size
    }

    /**
     * 新数据集size
     */
    override fun getNewListSize(): Int {
        return newList.size
    }

    /**
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBean = oldList[oldItemPosition]
        val newBean = newList[newItemPosition]

        // 如果有内容不相同就返回false
//            if (oldBean.name != newBean.name) {
//                return false
//            }

        if (!TextUtils.equals(oldBean.desc, newBean.desc)) {
            return false
        }

        //  //默认两个data内容是相同的
        return true
    }
}