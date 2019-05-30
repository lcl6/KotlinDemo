package com.snxun.kotlindemo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.snxun.kotlindemo.R
import kotlinx.android.synthetic.main.it_head_expand.view.*

/**
 * Created by liancl on 2019/5/30.
 *
 * JvmOverloads在有默认参数值的方法中使用 @JvmOverloads注解，则Kotlin就会暴露多个重载方法
 */
class ExpandView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var click: Boolean = false
    init {
        initView(context,attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandView)
        val title = typedArray.getString(R.styleable.ExpandView_ev_title)
        orientation = VERTICAL
        tv_title.text = title
        lin_title.setOnClickListener {
            click = !click
            hideView(click)
        }
        typedArray.recycle()

    }

    private fun hideView(click: Boolean) {

        img.setImageResource(if (click) R.drawable.ic_down else R.drawable.ic_up)
        val childCount = childCount
        if (childCount > 0) {
            for (i in 0 until childCount) {
                if (i > 0) {
                    getChildAt(i).visibility = if (click) View.GONE else View.VISIBLE
                }
            }
        }
    }
    fun setHide(hide: Boolean) {
        hideView(hide)
    }

}