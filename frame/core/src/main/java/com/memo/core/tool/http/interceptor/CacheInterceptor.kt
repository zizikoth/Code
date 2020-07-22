package com.memo.core.tool.http.interceptor

import com.blankj.utilcode.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * title:HTTP请求缓存
 * describe:不过支持Get请求
 *
 * @author memo
 * @date 2020-04-28 15:18
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CacheInterceptor(private val day: Int = 7) : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		var request = chain.request()
		if (!NetworkUtils.isAvailable()) {
			request = request.newBuilder()
				.cacheControl(CacheControl.FORCE_CACHE)
				.build()
		}
		val response = chain.proceed(request)
		if (!NetworkUtils.isAvailable()) {
			val maxAge = 60 * 60
			response.newBuilder()
				.removeHeader("Pragma")
				.header("Cache-Control", "public, max-age=$maxAge")
				.build()
		} else {
			val maxStale = 60 * 60 * 24 * day
			response.newBuilder()
				.removeHeader("Pragma")
				.header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
				.build()
		}
		return response
	}
}