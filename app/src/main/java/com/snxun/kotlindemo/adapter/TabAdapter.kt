package com.snxun.kotlindemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * viewpage 适配器
 * Created by liancl on 2019/2/28.
 */
class TabAdapter(
    fragmentManager: FragmentManager,
    private val list: List<String>?,
    private val fragmentList: List<Fragment>?
) :
    FragmentPagerAdapter(fragmentManager) {



    override fun getCount(): Int {
        return fragmentList?.size ?: 0
    }

    override fun getItem(position: Int): Fragment? {
       return fragmentList?.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list?.get(position)
    }

}