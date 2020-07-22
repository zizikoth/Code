@file:Suppress("DEPRECATION")

package com.memo.core.tool.dir

import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.core.tool.app.BaseApp
import com.memo.core.tool.helper.PermissionHelper

/**
 * title:本地文件夹地址
 * describe:
 *
 * @author zhou
 * @date 2019-07-23 15:26
 */
object LocalDir {

	/*** 本地缓存根目录 ***/
	val CACHE_ROOT_PATH = BaseApp.app.getExternalFilesDir("caches")

	/*** 本地图片压缩缓存 ***/
	val CACHE_DIR_COMPRESS: String = "$CACHE_ROOT_PATH/compress"

	/*** 本地拍照缓存 ***/
	val CACHE_DIR_CAPTURE: String = "$CACHE_ROOT_PATH/capture"

	/*** 本地图片裁剪缓存 ***/
	val CACHE_DIR_CROP: String = "$CACHE_ROOT_PATH/crop"

	/*** 本地文件缓存 ***/
	val CACHE_DIR_FILE: String = "$CACHE_ROOT_PATH/file"

	/*** 本地日志缓存 ***/
	val CACHE_DIR_LOG: String = "$CACHE_ROOT_PATH/log"

	/**
	 * 创建多媒体文件夹和
	 */
	@JvmStatic
	fun createLocalDir() {
		PermissionHelper.grantedStorage(BaseApp.app) {
			val dirCapture = FileUtils.createOrExistsDir(LocalDir.CACHE_DIR_CAPTURE)
			val dirCompress = FileUtils.createOrExistsDir(LocalDir.CACHE_DIR_COMPRESS)
			val dirCrop = FileUtils.createOrExistsDir(LocalDir.CACHE_DIR_CROP)
			val dirFile = FileUtils.createOrExistsDir(LocalDir.CACHE_DIR_FILE)
			val dirLog = FileUtils.createOrExistsDir(LocalDir.CACHE_DIR_LOG)
			LogUtils.iTag(
				"LocalDir",
				"dirCapture = $dirCapture ${LocalDir.CACHE_DIR_CAPTURE}",
				"dirCompress = $dirCompress ${LocalDir.CACHE_DIR_COMPRESS}",
				"dirCrop = $dirCrop ${LocalDir.CACHE_DIR_CROP}",
				"dirFile = $dirFile ${LocalDir.CACHE_DIR_FILE}",
				"dirLog = $dirLog ${LocalDir.CACHE_DIR_LOG}"
			)
		}
	}
}