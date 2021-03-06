package com.memo.core.tool.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * title:HTTP添加请求头
 * describe:
 *
 * @author memo
 * @date 2020-04-28 15:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HeaderInterceptor(private val headers: Map<String, String>? = null) : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val builder = chain.request()
			.newBuilder()
		headers?.forEach { builder.addHeader(it.key, it.value) }
		return chain.proceed(builder.build())
	}

}