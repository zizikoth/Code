package com.memo.home.api

import com.memo.base.entity.remote.ArticlePagerResponse
import com.memo.base.entity.remote.ArticleResponse
import com.memo.base.entity.remote.BannerResponse
import com.memo.base.entity.remote.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface HomeApi {

	/**
	 * 获取banner数据
	 */
	@GET("banner/json")
	suspend fun getBanner(): BaseResponse<ArrayList<BannerResponse>>

	/**
	 * 获取置顶文章集合数据
	 */
	@GET("article/top/json")
	suspend fun getTopArticles(): BaseResponse<ArrayList<ArticleResponse>>

	/**
	 * 获取首页文章数据
	 */
	@GET("article/list/{page}/json")
	suspend fun getHomeArticles(@Path("page") page: Int): BaseResponse<ArticlePagerResponse<ArrayList<ArticleResponse>>>

}