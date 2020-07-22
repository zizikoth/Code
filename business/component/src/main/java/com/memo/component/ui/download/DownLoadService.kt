package com.memo.component.ui.download

import android.app.IntentService
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.retrofit.RetrofitManager
import com.memo.core.tool.app.BaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-06-22 16:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
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