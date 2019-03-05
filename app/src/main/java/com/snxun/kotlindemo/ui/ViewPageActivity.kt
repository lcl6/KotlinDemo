package com.snxun.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

       var mList= ArrayList<String>()
       var mFragmentList = ArrayList<Fragment>()

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
            }

            override fun wpnr(wpnr:String) {
                ToastUtils.showShort(getContext(),"调用了wpnr")
            }

            override fun wpwr(param:Any,wpwr:String) {
                ToastUtils.showShort(getContext(),"调用了wpwr"+param?.toString())
            }

            override fun npwr(npwr:String) {
                ToastUtils.showShort(getContext(),"调用了npwr")
            }

        }
    }




//    fun toImplementsInterface(tag : String ){
//        val baseFrag = supportFragmentManager.findFragmentByTag(tag) as BaseInterFaceFragment
//        val functionManager = FunctionManager.getInstance()
//
//        baseFrag.setFunctionManger(functionManager.addFunction( object :FunctionNoParamNoResult(FriendFragment.TAG_FriendFragment){
//            override fun function() {
//                ToastUtils.showShort(getContext(),"点击了朋友")
//            }
//        }).addFunction(object :FunctionWithParamWithResult<Any,Any>(MainFragment.TAG_MainFragment){
//            override fun function(param: Any): String {
//
//                ToastUtils.showShort(getContext(),"点击了main")
//                ToastUtils.showShort(getContext(),"点击了main"+param.toString())
//                return "哈哈哈"
//            }
//
//        }))
//
//    }

}
