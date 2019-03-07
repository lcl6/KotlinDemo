package com.snxun.kotlindemo.utils.jsbridge.storage

import android.content.Context
import android.os.Build
import android.os.StatFs
import android.os.storage.StorageManager
import java.lang.reflect.Array

/**
 * Created by liancl on 2019/3/7.
 */
object StorageUtils {

    /**
     * 获得存储大小，单位byte
     * @param path
     */
    fun getStorageSize(path: String): Long {
        try {
            val fs = StatFs(path)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                fs.availableBlocksLong * fs.blockSizeLong
            } else fs.availableBlocksLong * fs.blockSizeLong
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0L
    }

    /**
     * 获取外置存储路径
     * @param context 上下文
     */
    fun getExternalStoragePath(context: Context): String? {
        try {
            val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
            val storageVolumeClass = Class.forName("android.os.storage.StorageVolume")
            val getVolumeList = storageManager.javaClass.getMethod("getVolumeList")
            val getPath = storageVolumeClass.getMethod("getPath")
            val isRemovable = storageVolumeClass.getMethod("isRemovable")
            val result = getVolumeList.invoke(storageManager)
            val length = Array.getLength(result)
            for (i in 0 until length) {
                val storageVolumeElement = Array.get(result, i)
                val path = getPath.invoke(storageVolumeElement) as String
                val removable = isRemovable.invoke(storageVolumeElement) as Boolean
                if (removable) {
                    return path
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 获取内置存储路径
     * @param context 上下文
     */
    fun getInternalStoragePath(context: Context): String? {
        try {
            val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
            val storageVolumeClass = Class.forName("android.os.storage.StorageVolume")
            val getVolumeList = storageManager.javaClass.getMethod("getVolumeList")
            val getPath = storageVolumeClass.getMethod("getPath")
            val isRemovable = storageVolumeClass.getMethod("isRemovable")
            val result = getVolumeList.invoke(storageManager)
            val length = Array.getLength(result)
            for (i in 0 until length) {
                val storageVolumeElement = Array.get(result, i)
                val path = getPath.invoke(storageVolumeElement) as String
                val removable = isRemovable.invoke(storageVolumeElement) as Boolean
                if (!removable) {
                    return path
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

}