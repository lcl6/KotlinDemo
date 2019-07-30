package com.snxun.kotlindemo.utils.notch

import android.annotation.SuppressLint
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import java.io.*
import java.util.*

/**
 * Created by liancl on 2019/7/30.
 *
 */
object RomUtils {
    private val HUAWEI = "huawei"
    private val VIVO = "vivo"
    private val XIAOMI = "xiaomi"
    private val OPPO = "oppo"

    private val VERSION_PROPERTY_HUAWEI = "ro.build.version.emui"
    private val VERSION_PROPERTY_VIVO = "ro.vivo.os.build.display.id"
    private val VERSION_PROPERTY_XIAOMI = "ro.build.version.incremental"
    private val VERSION_PROPERTY_OPPO = "ro.build.version.opporom"
    private val UNKNOWN = "unknown"

    private var bean: RomInfo? = null

    private fun RomUtils(){
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    /**
     * Return whether the rom is made by huawei.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isHuawei(): Boolean {
        return HUAWEI == getRomInfo().name
    }

    /**
     * Return whether the rom is made by vivo.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isVivo(): Boolean {
        return VIVO == getRomInfo().name
    }

    /**
     * Return whether the rom is made by xiaomi.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isXiaomi(): Boolean {
        return XIAOMI == getRomInfo().name
    }

    /**
     * Return whether the rom is made by oppo.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isOppo(): Boolean {
        return OPPO == getRomInfo().name
    }

    /**
     * Return the rom's information.
     *
     * @return the rom's information
     */
    fun getRomInfo(): RomInfo {
        if (bean != null) return bean as RomInfo
        bean = RomInfo()
        val brand = getBrand()
        val manufacturer = getManufacturer()
        if (isRightRom(brand, manufacturer, HUAWEI)) {
            bean!!.name = HUAWEI
            val version = getRomVersion(VERSION_PROPERTY_HUAWEI)
            val temp = version.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (temp.size > 1) {
                bean!!.version = temp[1]
            } else {
                bean!!.version = version
            }
            return bean as RomInfo
        }
        if (isRightRom(brand, manufacturer, VIVO)) {
            bean!!.name = VIVO
            bean!!.version = getRomVersion(VERSION_PROPERTY_VIVO)
            return bean as RomInfo
        }
        if (isRightRom(brand, manufacturer, XIAOMI)) {
            bean!!.name = XIAOMI
            bean!!.version = getRomVersion(VERSION_PROPERTY_XIAOMI)
            return bean as RomInfo
        }
        if (isRightRom(brand, manufacturer, OPPO)) {
            bean!!.name = OPPO
            bean!!.version = getRomVersion(VERSION_PROPERTY_OPPO)
            return bean as RomInfo
        } else {
            bean!!.name = manufacturer
        }
        bean!!.version = getRomVersion("")
        return bean as RomInfo
    }

    private fun isRightRom(brand: String, manufacturer: String, vararg names: String): Boolean {
        for (name in names) {
            if (brand.contains(name) || manufacturer.contains(name)) {
                return true
            }
        }
        return false
    }

    private fun getManufacturer(): String {
        try {
            val manufacturer = Build.MANUFACTURER
            if (!TextUtils.isEmpty(manufacturer)) {
                return manufacturer.toLowerCase()
            }
        } catch (ignore: Throwable) { /**/
        }

        return UNKNOWN
    }

    private fun getBrand(): String {
        try {
            val brand = Build.BRAND
            if (!TextUtils.isEmpty(brand)) {
                return brand.toLowerCase()
            }
        } catch (ignore: Throwable) { /**/
        }

        return UNKNOWN
    }

    private fun getRomVersion(propertyName: String): String {
        var ret = ""
        if (!TextUtils.isEmpty(propertyName)) {
            ret = getSystemProperty(propertyName)
        }
        if (TextUtils.isEmpty(ret) || ret == UNKNOWN) {
            try {
                val display = Build.DISPLAY
                if (!TextUtils.isEmpty(display)) {
                    ret = display.toLowerCase()
                }
            } catch (ignore: Throwable) { /**/
            }

        }
        return if (TextUtils.isEmpty(ret)) {
            UNKNOWN
        } else ret
    }

    private fun getSystemProperty(name: String): String {
        var prop = getSystemPropertyByShell(name)
        if (!TextUtils.isEmpty(prop)) return prop
        prop = getSystemPropertyByStream(name)
        if (!TextUtils.isEmpty(prop)) return prop
        return if (Build.VERSION.SDK_INT < 28) {
            getSystemPropertyByReflect(name)
        } else prop
    }

    private fun getSystemPropertyByShell(propName: String): String {
        val line: String
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $propName")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            val ret = input.readLine()
            if (ret != null) {
                return ret
            }
        } catch (ignore: IOException) {
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (ignore: IOException) { /**/
                }

            }
        }
        return ""
    }

    private fun getSystemPropertyByStream(key: String): String {
        try {
            val prop = Properties()
            val `is` = FileInputStream(
                File(Environment.getRootDirectory(), "build.prop")
            )
            prop.load(`is`)
            return prop.getProperty(key, "")
        } catch (ignore: Exception) { /**/
        }

        return ""
    }

    private fun getSystemPropertyByReflect(key: String): String {
        try {
            @SuppressLint("PrivateApi")
            val clz = Class.forName("android.os.SystemProperties")
            val getMethod = clz.getMethod("get", String::class.java, String::class.java)
            return getMethod.invoke(clz, key, "") as String
        } catch (e: Exception) { /**/
        }

        return ""
    }

    class RomInfo {
        var name: String? = null
        var version: String? = null

        override fun toString(): String {
            return "RomInfo{name=" + name +
                    ", version=" + version + "}"
        }
    }
}