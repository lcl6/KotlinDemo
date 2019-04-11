package com.snxun.kotlindemo.utils.jsbridge.dif

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.snxun.kotlindemo.bean.NewsListBean

/**
 * Created by liancl on 2019/4/3.
 *
 */
class DiffItemCallback: DiffUtil.ItemCallback<NewsListBean>() {
    override fun areItemsTheSame(oldItem: NewsListBean, newItem: NewsListBean): Boolean {
        return TextUtils.equals(oldItem.desc, newItem.desc)
    }

    override fun areContentsTheSame(oldItem: NewsListBean, newItem: NewsListBean): Boolean {
        return TextUtils.equals(oldItem.id, newItem.id)
    }
}