package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.tabs.TabLayout
import com.lodz.android.corekt.utils.ToastUtils
import com.snxun.kotlindemo.R
import com.snxun.kotlindemo.adapter.TabAdapter
import com.snxun.kotlindemo.base.BaseInterActivity
import com.snxun.kotlindemo.fragment.FriendFragment
import com.snxun.kotlindemo.fragment.MainFragment
import com.snxun.kotlindemo.fragment.MyFragment

/**
 * Created by liancl on 2019/2/26.
 */
class ViewPageActivity : BaseInterActivity() {


    @BindView(R.id.vp)
    lateinit var viewPager: ViewPager

    @BindView(R.id.tb_layout)
    lateinit var tabLayout: TabLayout


    var mList= ArrayList<String>()
    private var mFragmentList = ArrayList<Fragment>()

    companion object {
        fun start(context: Context){
            val intent = Intent()
            intent.setClass(context,ViewPageActivity::class.java)
            context.startActivity(intent)

        }
    }


    override fun getLayoutId(): Int {
        return R.layout.ac_viewpager
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        ButterKnife.bind(this)

        initViewPage()
        showStatusCompleted()
    }

    private fun initViewPage() {
        mList.add("首页")
        mList.add("朋友圈")
        mList.add("我的")


        val mainFragment = MainFragment.instance()
        val mainFragment2 = FriendFragment.instance()
        val mainFragment3 = MyFragment.instance()
        mFragmentList.add(mainFragment)
        mFragmentList.add(mainFragment2)
        mFragmentList.add(mainFragment3)
        var mAdapter= TabAdapter(supportFragmentManager,mList,mFragmentList)
        viewPager.adapter=mAdapter
        viewPager.offscreenPageLimit=mList.size
        tabLayout.tabMode=TabLayout.MODE_FIXED
        tabLayout.setupWithViewPager(viewPager)
        addTabNum("1","2","3")

    }

    override fun onClickBackBtn() {
        super.onClickBackBtn()
        finish()
    }


    override fun setListeners() {
        super.setListeners()

        listener=object:Listener{
            override fun npnr(npnr:String) {
                ToastUtils.showShort(getContext(),"调用了npnr")
                addTabNum("6","7","8")
            }

            override fun wpnr(wpnr:String) {
                ToastUtils.showShort(getContext(),"调用了wpnr")
                addTabNum("7","8","9")
            }

            override fun wpwr(param:Any,wpwr:String) {
                ToastUtils.showShort(getContext(),"调用了wpwr"+param?.toString())
                addTabNum("11","12","14")
            }

            override fun npwr(npwr:String) {
                addTabNum("15","16","17")
                ToastUtils.showShort(getContext(),"调用了npwr")
            }

        }
    }


    fun addTabNum( mainNum:String,pyqNum:String,myNum:String){

        for (index in 0 until mList.size){
            tabLayout.getTabAt(index)!!.customView = null
            val view = LayoutInflater.from(getContext()).inflate(R.layout.tab_item, null)
            val tvTitle = view.findViewById<TextView>(R.id.tv_title)
            val tvSum = view.findViewById<TextView>(R.id.tv_sum)
            when(index){
               0 -> {
                    tvTitle.text = "首页"; tvSum.text = mainNum
                }
                1 -> {
                    tvTitle.text = "朋友圈"; tvSum.text = pyqNum
                }
                2 -> {
                    tvTitle.text = "我的"; tvSum.text = myNum
                }
            }
            tabLayout.getTabAt(index)!!.customView = view
        }
    }



}
