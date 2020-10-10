package com.memo.core.tool.helper


import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.contentValuesOf
import com.blankj.utilcode.util.CloseUtils
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.FileUtils
import com.memo.core.tool.app.BaseApp
import okhttp3.ResponseBody
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-10-10 13:36
 * @email zhou_android@163.com
 *
 * Talk is cheap Show me the code.
 */
object FileHelper {

	/**
	 * 将文件存储到Download文件夹下
	 * @param dirName 文件夹名称
	 * @param fileName 文件名称
	 * @param inputStream 输入流
	 */
	@JvmStatic
	fun saveFile2Download(
		dirName: String,
		fileName: String,
		response: ResponseBody,
		listener: FileIOUtils.OnProgressUpdateListener? = null
	): Boolean {
		return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
			val contentValues = contentValuesOf(
				MediaStore.Downloads.DISPLAY_NAME to fileName,
				MediaStore.Downloads.MIME_TYPE to getMimeType(fileName),
				MediaStore.Downloads.DATE_TAKEN to System.currentTimeMillis(),
				MediaStore.Downloads.RELATIVE_PATH to Environment.DIRECTORY_DOWNLOADS + "/$dirName"
			)
			val contentResolver = BaseApp.app.contentResolver
			val uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
			if (uri == null) {
				false
			} else {
				val ins = response.byteStream()
				val total = response.contentLength()
				var sum = 0L
				val os = contentResolver.openOutputStream(uri)
				if (os != null) {
					val buffer = ByteArray(102400)
					var len = ins.read(buffer)
					while (len != -1) {
						sum += len
						os.write(buffer, 0, len)
						listener?.onProgressUpdate(sum * 100.0 / total)
						len = ins.read(buffer)
					}
					os.flush()
					CloseUtils.closeIO(ins, os)
					true
				} else {
					false
				}
			}
		} else {
			val filePath =
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/$dirName/$fileName"
			FileUtils.createFileByDeleteOldFile(filePath)
			FileIOUtils.writeFileFromBytesByStream(filePath, response.bytes(), listener)
		}
	}

	/**
	 * 根据文件名称获取MimeType
	 * @param fileName 文件名称
	 * @return MimeType
	 */
	@JvmStatic
	fun getMimeType(fileName: String): String {
		val type = "*/*"
		val index = fileName.lastIndexOf(".")
		return if (index < 0) {
			type
		} else {
			when (fileName.substring(index, fileName.length).toLowerCase(Locale.getDefault())) {
				".3gp" -> "video/3gpp"
				".apk" -> "application/vnd.android.package-archive"
				".asf" -> "video/x-ms-asf"
				".avi" -> "video/x-msvideo"
				".bin" -> "application/octet-stream"
				".bmp" -> "image/bmp"
				".c" -> "text/plain"
				".class" -> "application/octet-stream"
				".conf" -> "text/plain"
				".cpp" -> "text/plain"
				".doc" -> "application/msword"
				".docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
				".xls" -> "application/vnd.ms-excel"
				".xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
				".exe" -> "application/octet-stream"
				".gif" -> "image/gif"
				".gtar" -> "application/x-gtar"
				".gz" -> "application/x-gzip"
				".h" -> "text/plain"
				".htm" -> "text/html"
				".html" -> "text/html"
				".jar" -> "application/java-archive"
				".java" -> "text/plain"
				".jpeg" -> "image/jpeg"
				".jpg" -> "image/jpeg"
				".js" -> "application/x-javascript"
				".log" -> "text/plain"
				".m3u" -> "audio/x-mpegurl"
				".m4a" -> "audio/mp4a-latm"
				".m4b" -> "audio/mp4a-latm"
				".m4p" -> "audio/mp4a-latm"
				".m4u" -> "video/vnd.mpegurl"
				".m4v" -> "video/x-m4v"
				".mov" -> "video/quicktime"
				".mp2" -> "audio/x-mpeg"
				".mp3" -> "audio/x-mpeg"
				".mp4" -> "video/mp4"
				".mpc" -> "application/vnd.mpohun.certificate"
				".mpe" -> "video/mpeg"
				".mpeg" -> "video/mpeg"
				".mpg" -> "video/mpeg"
				".mpg4" -> "video/mp4"
				".mpga" -> "audio/mpeg"
				".msg" -> "application/vnd.ms-outlook"
				".ogg" -> "audio/ogg"
				".pdf" -> "application/pdf"
				".png" -> "image/png"
				".pps" -> "application/vnd.ms-powerpoint"
				".ppt" -> "application/vnd.ms-powerpoint"
				".pptx" -> "application/vnd.openxmlformats-officedocument.presentationml.presentation"
				".prop" -> "text/plain"
				".rc" -> "text/plain"
				".rmvb" -> "audio/x-pn-realaudio"
				".rtf" -> "application/rtf"
				".sh" -> "text/plain"
				".tar" -> "application/x-tar"
				".tgz" -> "application/x-compressed"
				".txt" -> "text/plain"
				".wav" -> "audio/x-wav"
				".wma" -> "audio/x-ms-wma"
				".wmv" -> "audio/x-ms-wmv"
				".wps" -> "application/vnd.ms-works"
				".xml" -> "text/plain"
				".z" -> "application/x-compress"
				".zip" -> "application/x-zip-compressed"
				else -> type
			}
		}
	}
}