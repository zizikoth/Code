package com.memo.base.api

import android.net.ParseException
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.*
import com.google.gson.JsonParseException
import com.memo.core.tool.dir.LocalDir
import com.memo.core.tool.ext.toast
import org.json.JSONException
import retrofit2.HttpException
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

/**
 * title:异常处理
 * describe:
 *
 * @author zhou
 * @date 2019-07-09 10:41
 */
object ExceptionHandler {

	fun handleException(exception: Throwable): Int {
		//错误日志打印
		LogUtils.eTag("HTTP ERROR", exception.toString())
		//在这里把错误日志存储到本地
		saveErrorLog2Local(exception)
		val e = when (exception) {
			// 服务器返回的错误
			is ApiException -> exception
			// 解析错误
			is JsonParseException,
			is JSONException,
			is ParseException -> ApiException(ApiCode.ServerErrorCode, "数据解析失败")
			// 连接错误
			is HttpException,
			is ConnectException,
			is SocketException -> ApiException(ApiCode.ServerErrorCode, "无法连接服务器")
			// 网络错误
			is UnknownHostException -> ApiException(ApiCode.NetworkErrorCode, "网络异常")
			// 未知错误
			else -> ApiException(ApiCode.ServerErrorCode, "服务器异常")
		}
		toast(e.message)
		return e.code
	}

	private fun saveErrorLog2Local(exception: Throwable) {

		//没有存储权限 直接返回
		if (!PermissionUtils.isGranted(PermissionConstants.STORAGE)) return

		//创建错误日志文件夹
		val file = File(LocalDir.CACHE_DIR_LOG)
		FileUtils.createOrExistsDir(file)

		//获取详细错误信息
		var info: String
		var sw: StringWriter? = null
		var pw: PrintWriter? = null
		try {
			sw = StringWriter()
			pw = PrintWriter(sw)
			exception.printStackTrace(pw)
			pw.flush()
			sw.flush()
			info = sw.toString()
		} catch (e: Exception) {
			info = exception.toString()
		} finally {
			CloseUtils.closeIO(sw, pw)
		}
		//信息存储
		val log = "${file.absolutePath}/${TimeUtils.getNowString()}.txt"
		FileIOUtils.writeFileFromString(log, info)
	}
}