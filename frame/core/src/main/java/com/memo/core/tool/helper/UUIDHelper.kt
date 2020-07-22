package com.memo.core.tool.helper

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.PhoneUtils
import com.memo.core.tool.app.BaseApp
import com.memo.core.tool.ext.md5

/**
 * title:获取应用uuid
 * describe:
 *
 * @author memo
 * @date 2020-01-09 10:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object UUIDHelper {

	private var uuid: String = ""

	/**
	 * 通过刷机是可以改变数据的
	 * 恢复出厂设置AndroidId变化
	 */
	fun getUUID(): String {
		if (uuid.isEmpty()) {
			if (ActivityCompat.checkSelfPermission(
					BaseApp.app,
					Manifest.permission.READ_PHONE_STATE
				) == PackageManager.PERMISSION_GRANTED
			) {
				uuid = PhoneUtils.getIMEI()
			}
			if (uuid.isEmpty()) {
				uuid = StringBuilder()
					.append(Build.BOARD).append(Build.BRAND)
					.append(Build.DEVICE).append(Build.DISPLAY)
					.append(Build.FINGERPRINT).append(Build.HOST)
					.append(Build.MANUFACTURER).append(Build.MODEL)
					.append(Build.PRODUCT).append(Build.TAGS)
					.append(Build.TYPE).append(Build.USER)
					.append(DeviceUtils.getAndroidID())
					.append(DeviceUtils.getMacAddress())
					.toString()
			}
			uuid = uuid.md5()
		}
		return uuid
	}
}