package com.snxun.kotlindemo.fragment


import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.base.BaseInterFaceFragment
import kotlinx.android.synthetic.main.fg_friend.*
/**
 * 朋友圈
 * Created by liancl on 2019/3/1.
 */
class FriendFragment : BaseInterFaceFragment() {






    companion object {

        val TAG_FriendFragment : String="aaaa"

        fun  instance() :FriendFragment {
            val friendFragment = FriendFragment()
            return friendFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fg_friend
    }

    override fun findViews(view: View, savedInstanceState: Bundle?) {
        super.findViews(view, savedInstanceState)
        ButterKnife.bind(this,view)
        showStatusCompleted()

    }

    override fun initData(view: View) {
        super.initData(view)

    }

    override fun getNpnrFragTag(): String {
        return TAG_FriendFragment
    }

    override fun setListeners(view: View) {
        super.setListeners(view)
        tv_click_pyq.setOnClickListener {
            functionManager.invokeFunction(TAG_FriendFragment)
        }
    }



}