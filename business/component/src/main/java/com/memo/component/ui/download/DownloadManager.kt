package com.memo.component.ui.download

import com.blankj.utilcode.util.CloseUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-06-23 15:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

class DownloadManager {

	interface OnProgressListener {
		fun onProgress(progress: Int)
		fun onFinish(file: File)
	}

	private var listener: OnProgressListener? = null

	private object Holder {
		val instance = DownloadManager()
	}

	companion object {
		fun get() = Holder.instance
	}

	fun start(url: String, path: String, name: String, listener: OnProgressListener) {
		this.listener = listener
		DownLoadService.start(url, path, name)
	}

	fun saveFile(responseBody: ResponseBody, destFileDir: String, destFileName: String) {
		var ins: InputStream? = null
		var fos: FileOutputStream? = null
		val buf = ByteArray(2048)
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
				listener?.onProgress((sum * 100 / total).toInt())
				LogUtils.iTag("download", (sum * 100 / total).toInt(), total)
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