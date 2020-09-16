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
import com.memo.core.tool.ext.onClick
import com.memo.core.tool.ext.toast
import kotlinx.android.synthetic.main.activity_file.*
import java.io.File

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

	/*** 绑定布局id ***/
	override fun bindLayoutRes(): Int = R.layout.activity_file

	/*** 进行初始化操作 ***/
	override fun initialize() {
		mBtnFile.onClick {
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
				val fileValue = ContentValues()
				fileValue.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, "aaa.jpg")
				fileValue.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg")
				fileValue.put(MediaStore.Images.ImageColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "cache")
				contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, fileValue)?.let {
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
				val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "/cache/aaa.jpg"
				MediaScannerConnection.scanFile(mContext, arrayOf(filePath), arrayOf("image/jpeg")) { path, uri ->
					LogUtils.iTag("file", path)
					val result = contentResolver.delete(uri, null, null)
					toast(if (result > 0) "删除成功" else "删除失败")
				}
			}
		}
	}
}