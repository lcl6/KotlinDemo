package com.snxun.kotlindemo.utils.recycler

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

/**
 * 分割线
 * Created by liancl on 2019/5/29.
 *
 */
class CommanDecoration : RecyclerView.ItemDecoration() {

    private var mDividerHeight: Float= 0.0f
    private var margin: Float = 0.0f
    private var mPaint: Paint = Paint()
    init {
        mPaint.color= 0xffffff
        mPaint.isAntiAlias = true          //抗锯齿
    }

    //通过建造者模式来设置三个属性
    //设置左右偏移(默认是设置的一样的，若需要自己更改)
    fun setMargin( margin :Float):CommanDecoration{
        this.margin = margin
        return this
    }

    //通过建造者模式来设置三个属性
    //设置颜色(默认是设置的一样的，若需要自己更改)
    fun setColor(@ColorInt color :Int):CommanDecoration{
        mPaint.color=color
        return this
    }

    //通过建造者模式来设置三个属性
    //设置分割线高度(默认是设置的一样的，若需要自己更改)
    fun setDividerHeight( mDividerHeight :Float):CommanDecoration{
        this.mDividerHeight = mDividerHeight
        return this
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        //第一个ItemView不需要在上面绘制分割线
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mDividerHeight.toInt() //指相对itemView顶部的偏移量
        }

    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        if(childCount<=0){
            return
        }

        //因为getItemOffsets是针对每一个ItemView，而onDraw方法是针对RecyclerView本身，所以需要循环遍历来设置
        for (i in 0 until childCount){
            val view = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(view)
            //第一个ItemView不需要绘制
            if(index==0){
                continue
            }
            val divideTop = view.top - mDividerHeight
            val divideLeft = parent.paddingLeft + margin
            val divideBottom = view.top
            val divideRight = parent.width - parent.paddingRight - margin
            val color = mPaint.color
            c.drawRect(divideLeft, divideTop, divideRight, divideBottom.toFloat(), mPaint)
        }
    }
}