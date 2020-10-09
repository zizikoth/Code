package com.memo.core.tool.dir

import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.core.tool.app.BaseApp

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
		val dirCaptureCreated = FileUtils.createOrExistsDir(CACHE_DIR_CAPTURE)
		val dirCompressCreated = FileUtils.createOrExistsDir(CACHE_DIR_COMPRESS)
		val dirCropCreated = FileUtils.createOrExistsDir(CACHE_DIR_CROP)
		val dirFileCreated = FileUtils.createOrExistsDir(CACHE_DIR_FILE)
		val dirLogCreated = FileUtils.createOrExistsDir(CACHE_DIR_LOG)
		LogUtils.iTag(
			"LocalDir",
			"dirCapture = $dirCaptureCreated $CACHE_DIR_CAPTURE",
			"dirCompress = $dirCompressCreated $CACHE_DIR_COMPRESS",
			"dirCrop = $dirCropCreated $CACHE_DIR_CROP",
			"dirFile = $dirFileCreated $CACHE_DIR_FILE",
			"dirLog = $dirLogCreated $CACHE_DIR_LOG"
		)
	}
}