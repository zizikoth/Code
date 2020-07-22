package com.memo.home.api

import com.memo.base.entity.remote.*
import com.memo.base.manager.retrofit.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:26
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object HomeRepository {

	private val service by lazy { RetrofitManager.create(HomeApi::class.java) }

	/**
	 * 获取首页数据
	 * @param page Int 页码
	 */
	suspend fun getHomeData(page: Int): BaseResponse<HomeZipResponse> {
		return withContext(Dispatchers.IO) {
			val articles = withContext(Dispatchers.Default) { getHomeArticles(page) }.data
			if (page == 0) {
				val topArticles = withContext(Dispatchers.Default) { getTopArticles() }.data
				topArticles.forEach { it.isTop = true }
				val banner = withContext(Dispatchers.Default) { getBanners() }.data
				topArticles.addAll(articles.datas)
				BaseResponse(HomeZipResponse(topArticles, articles.hasMore(), banner))
			} else {
				BaseResponse(HomeZipResponse(articles.datas, articles.hasMore()))
			}
		}
	}

	/**
	 * 首页置顶文章
	 */
	private suspend fun getTopArticles(): BaseResponse<ArrayList<ArticleResponse>> =
		service.getTopArticles()

	/**
	 * 首页的轮播图
	 */
	private suspend fun getBanners(): BaseResponse<ArrayList<BannerResponse>> = service.getBanner()

	/**
	 * 获取首页文章
	 */
	private suspend fun getHomeArticles(page: Int): BaseResponse<ArticlePagerResponse<ArrayList<ArticleResponse>>> =
		service.getHomeArticles(page)

}