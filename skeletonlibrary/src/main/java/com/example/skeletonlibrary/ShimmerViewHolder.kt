package com.example.skeletonlibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ethanhua on 2017/7/29.
 */

class ShimmerViewHolder(inflater: LayoutInflater, parent: ViewGroup, innerViewResId: Int) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_shimmer, parent, false)) {

    init {
        val layout = itemView as ViewGroup
        val view = inflater.inflate(innerViewResId, layout, false)
        val lp = view.layoutParams
        if (lp != null) {
            layout.layoutParams = lp
        }
        layout.addView(view)
    }
}
