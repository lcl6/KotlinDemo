package com.example.skeletonlibrary

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ethanhua on 2017/7/29.
 */

object Skeleton {

    fun bind(recyclerView: RecyclerView): RecyclerViewSkeletonScreen.Builder {
        return RecyclerViewSkeletonScreen.Builder(recyclerView)
    }

    fun bind(view: View): ViewSkeletonScreen.Builder {
        return ViewSkeletonScreen.Builder(view)
    }

}
