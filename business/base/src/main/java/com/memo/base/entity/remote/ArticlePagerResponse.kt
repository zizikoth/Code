package com.memo.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 17:48
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class ArticlePagerResponse<T>(
	var datas: T,
	var curPage: Int,
	var offset: Int,
	var over: Boolean,
	var pageCount: Int,
	var size: Int,
	var total: Int
) {
	fun isEmpty(): Boolean {
		return if (datas is List<*>) {
			(datas as List<*>).isEmpty()
		} else {
			datas == null
		}
	}

	fun hasMore() = !over

}