package com.memo.component.ui.file

import android.app.IntentService
import android.content.Intent
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.retrofit.RetrofitManager
import com.memo.component.ui.update.UpdateApi
import com.memo.core.tool.app.BaseApp
import com.memo.core.tool.helper.FileHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody


/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-10-10 14:54
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DownloadManager {

	companion object {
		fun get() = Holder.instance
	}

	private object Holder {
		val instance = DownloadManager()
	}

	private var listener: FileIOUtils.OnProgressUpdateListener? = null

	fun download(url: String, path: String, name: String, listener: FileIOUtils.OnProgressUpdateListener) {
		this.listener = listener
		DownloadService.start(url, path, name)
	}

	fun saveFile(responseBody: ResponseBody, destFileDir: String, destFileName: String) {
		FileHelper.saveFile2Download(destFileDir, destFileName, responseBody, this.listener)
	}

}

@Suppress("DEPRECATION")
class DownloadService : IntentService("DownloadService") {

	companion object {
		const val DownloadUrl = "DOWNLOAD_URL"
		const val DownloadName = "DOWNLOAD_NAME"
		const val DownloadPath = "DOWNLOAD_PATH"
		fun start(url: String, path: String, name: String) {
			val context = BaseApp.app.applicationContext
			context.startService(
				Intent(context, DownloadService::class.java)
					.putExtra(DownloadUrl, url)
					.putExtra(DownloadPath, path)
					.putExtra(DownloadName, name)
			)
		}
	}

	override fun onHandleIntent(intent: Intent?) {
		intent?.let {
			val url = it.getStringExtra(DownloadUrl)
			val path = it.getStringExtra(DownloadPath)
			val name = it.getStringExtra(DownloadName)
			LogUtils.iTag("download", "url = $url\npath = $path\nname = $name")
			if (!url.isNullOrEmpty() && !path.isNullOrEmpty() && !name.isNullOrEmpty()) {
				GlobalScope.launch(Dispatchers.IO) {
					val responseBody = RetrofitManager.create(UpdateApi::class.java).update(url)
					DownloadManager.get().saveFile(responseBody, path, name)
				}
			}
		}
	}
}
