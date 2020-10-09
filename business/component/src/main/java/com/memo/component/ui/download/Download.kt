package com.memo.component.ui.download

import android.app.IntentService
import android.content.Intent
import com.blankj.utilcode.util.CloseUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.retrofit.RetrofitManager
import com.memo.core.tool.app.BaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-24 11:35
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface OnProgressListener {
	fun onProgress(progress: Double)
	fun onFinish(file: File)
}

interface DownLoadApi {
	@Streaming
	@GET
	suspend fun download(@Url url: String): ResponseBody
}

class DownloadManager {

	companion object {
		fun get() = Holder.instance
	}

	private object Holder {
		val instance = DownloadManager()
	}

	private var listener: OnProgressListener? = null

	fun start(url: String, path: String, name: String, listener: OnProgressListener) {
		this.listener = listener
		DownLoadService.start(url, path, name)
	}

	fun saveFile(responseBody: ResponseBody, destFileDir: String, destFileName: String) {
		var ins: InputStream? = null
		var fos: FileOutputStream? = null
		val buf = ByteArray(102400)
		var len: Int
		try {
			ins = responseBody.byteStream()
			val total = responseBody.contentLength()
			var sum: Long = 0
			val file = File(destFileDir, destFileName)
			FileUtils.createFileByDeleteOldFile(file)
			fos = FileOutputStream(file)
			len = ins.read(buf)
			while (len != -1) {
				sum += len.toLong()
				fos.write(buf, 0, len)
				listener?.onProgress(sum * 100.0 / total)
				LogUtils.iTag("download", sum * 100.0 / total, total)
				len = ins.read(buf)
			}
			fos.flush()
			listener?.onFinish(file)
		} catch (e: Exception) {
			LogUtils.iTag("download", e)
		} finally {
			CloseUtils.closeIO(ins, fos)
		}
	}

}

@Suppress("DEPRECATION")
class DownLoadService : IntentService("DownLoadService") {

	companion object {
		const val DownUrl = "DOWN_URL"
		const val DownName = "DOWN_NAME"
		const val DownPath = "DOWN_PATH"
		fun start(url: String, path: String, name: String) {
			val context = BaseApp.app.applicationContext
			context.startService(
				Intent(context, DownLoadService::class.java)
					.putExtra(DownUrl, url)
					.putExtra(DownPath, path)
					.putExtra(DownName, name)
			)
		}
	}

	override fun onHandleIntent(intent: Intent?) {
		intent?.let {
			val url = it.getStringExtra(DownUrl)
			val path = it.getStringExtra(DownPath)
			val name = it.getStringExtra(DownName)
			LogUtils.iTag("download", "url = $url\npath = $path\nname = $name")
			if (!url.isNullOrEmpty() && !path.isNullOrEmpty() && !name.isNullOrEmpty()) {
				GlobalScope.launch(Dispatchers.IO) {
					val responseBody = RetrofitManager.create(DownLoadApi::class.java).download(url)
					DownloadManager.get().saveFile(responseBody, path, name)
				}
			}
		}
	}


}