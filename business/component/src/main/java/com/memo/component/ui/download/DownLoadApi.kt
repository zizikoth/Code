package com.memo.component.ui.download

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-06-22 16:44
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface DownLoadApi {
	@Streaming
	@GET
	suspend fun download(@Url url: String): ResponseBody

}