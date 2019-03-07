package com.snxun.kotlindemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import com.lodz.android.corekt.utils.ToastUtils
import com.lodz.android.pandora.base.activity.BaseActivity
import com.snxun.kotlindemo.dialog.CheckDialog
import permissions.dispatcher.*

/**
 * Created by liancl on 2019/2/20.
 */
@RuntimePermissions
class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.ac_splash
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)


showStatusCompleted()
    }

    override fun initData() {
        super.initData()
        needPermissionWithPermissionCheck()

    }


    @NeedsPermission(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun needPermission() {
        init()
    }

    private fun init() {
        MainActivity.start(this)
        finish()
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun showRationale(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun permissionDeny() {
        needPermissionWithPermissionCheck()
    }

    @OnNeverAskAgain(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun neverAsk() {//跳转设置打开权限

        var dialog =CheckDialog(getContext())
        dialog.setListener{type: Int ->
            when(type){
                dialog.TYPE_NEGATIVE-> {ToastUtils.showShort(getContext(),"TYPE_NEGATIVE");jumpAppDetailSetting(getContext())}
                dialog.TYPE_PPOSITIVE->{ ToastUtils.showShort(getContext(),"TYPE_PPOSITIVE");dialog.dismiss(); needPermissionWithPermissionCheck()}
            }
        }
        dialog.show()
    }

    /**
     * 根据包名打开对应的设置界面
     * @param context 上下文
     */
    private fun jumpAppDetailSetting(context: Context) {
        var intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + context.packageName)
        context.startActivity(intent)
    }


}