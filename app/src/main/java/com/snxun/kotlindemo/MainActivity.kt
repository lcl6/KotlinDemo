package com.snxun.kotlindemo

import android.Manifest
import android.annotation.SuppressLint
import android.widget.TextView
import android.widget.Toast
import com.lodz.android.corekt.utils.ToastUtils
import com.lodz.android.pandora.base.activity.BaseActivity
import permissions.dispatcher.*

/**
 * Created by liancl on 2019/2/13.
 *
 */
@RuntimePermissions
class MainActivity : BaseActivity(){
    override fun getLayoutId(): Int {
       return R.layout. activity_main
    }

    override fun initData() {
        super.initData()
        goneTitleBar()
        showStatusCompleted()
        findViewById<TextView>(R.id.tv_btn).setOnClickListener {

            mListener?.invoke("你好")
        }

        setOnMyClick { mag ->
            Toast.makeText(this,mag,Toast.LENGTH_LONG).show()
        }
        needWithPermissionCheck()


    }
    var mListener: ((String) -> Unit)? =null

    fun setOnMyClick(listener: (mag:String)->Unit){
        mListener=listener
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    fun need() {
        ToastUtils.showShort(getContext(),"need")
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRation(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun deny() {
        needWithPermissionCheck()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun neverAsk() {
    }





}

