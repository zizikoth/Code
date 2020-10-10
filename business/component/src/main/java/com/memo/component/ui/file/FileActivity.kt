package com.memo.component.ui.file

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import com.blankj.utilcode.util.LogUtils
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.app.BaseApp
import com.memo.core.tool.ext.keep2Decimal
import com.memo.core.tool.ext.onClick
import com.memo.core.tool.ext.toast
import com.memo.core.tool.helper.FileHelper
import kotlinx.android.synthetic.main.activity_file.*

/**
 * title:Android 10 在媒体资源库中存储文件
 * describe:
 * @see https://blog.csdn.net/mylike_45/article/details/107793024
 * @author memo
 * @date 2020-09-03 11:03
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class FileActivity : BaseActivity() {

	private val bitmap by lazy {
		val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(bitmap)
		val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLUE }
		canvas.drawCircle(50f, 50f, 50f, paint)
		bitmap
	}
	private val downUrl = "https://ucan.25pp.com/Wandoujia_web_seo_baidu_homepage.apk"

	/*** 绑定布局id ***/
	override fun bindLayoutRes(): Int = R.layout.activity_file

	/*** 进行初始化操作 ***/
	override fun initialize() {
		mBtnFile.onClick {
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
				val fileValue = ContentValues()
				fileValue.put(MediaStore.Downloads.DISPLAY_NAME, "aaa.jpg")
				fileValue.put(MediaStore.Downloads.MIME_TYPE, "image/jpeg")
				fileValue.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/cache")
				contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, fileValue)?.let {
					LogUtils.iTag("file", it)
					contentResolver.openOutputStream(it)?.let { os ->
						bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
						os.flush()
						os.close()
						toast("保存成功")
					}
				}
			}
		}
		mBtnDelete.onClick {
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
				val filePath =
					Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/cache/aaa.jpg"
				MediaScannerConnection.scanFile(BaseApp.app, arrayOf(filePath), arrayOf(FileHelper.getMimeType(filePath))) { path, uri ->
					LogUtils.iTag("file", path)
					val result = contentResolver.delete(uri, null, null)
					toast(if (result > 0) "删除成功" else "删除失败")
				}
			}
		}
		mBtn2Download.onClick {
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
				DownloadManager.get().download(downUrl, "下载文件", "temp.apk") {
					mContext.runOnUiThread { mTvProgress.text = "下载进度：" + it.keep2Decimal() }
				}
			}
		}
	}
}