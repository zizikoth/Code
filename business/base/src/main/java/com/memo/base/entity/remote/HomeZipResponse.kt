package com.memo.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 18:05
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeZipResponse(
	val articles: ArrayList<ArticleResponse>,
	val hasMore: Boolean,
	val banner: ArrayList<BannerResponse>? = null
)