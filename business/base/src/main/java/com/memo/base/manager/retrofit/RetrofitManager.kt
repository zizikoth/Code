package com.memo.base.manager.retrofit

import com.memo.base.app.AppConfig
import com.memo.base.app.AppConstant
import com.memo.core.tool.helper.GsonHelper
import com.memo.core.tool.http.interceptor.HttpLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * title: 网络请求管理类
 * describe: Retrofit已经支持协程，可以直接使用
 *
 * @author memo
 * @date 2020-04-28 14:24
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RetrofitManager {

	private var retrofit: Retrofit


	companion object {
		private val instance: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
			RetrofitManager()
		}

		fun <T> create(service: Class<T>): T = instance.retrofit.create(service)
	}

	init {
		val okHttp = OkHttpClient.Builder()
			.addInterceptor(HttpLogInterceptor(AppConfig.isOpenLog, "Http"))
			.readTimeout(10, TimeUnit.SECONDS)
			.connectTimeout(10, TimeUnit.SECONDS)
			.writeTimeout(10, TimeUnit.SECONDS)
			.build()
		retrofit = Retrofit.Builder()
			.baseUrl(AppConstant.Api.BASE_URL)
			.client(okHttp)
			.addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
			.build()
	}

}