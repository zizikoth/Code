package com.memo.base.entity.local

/**
 * title:页面数据
 * describe:
 *
 * @author memo
 * @date 2020-04-29 18:18
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class UiData<T> private constructor(
	val data: T?,
	val isRefresh: Boolean,
	val hasMore: Boolean
) {
	companion object {
		/**
		 * 返回成功数据
		 * @param data      数据源
		 * @param isRefresh 是否是刷新
		 * @param hasMore   是否有更多
		 */
		fun <T> success(data: T, isRefresh: Boolean, hasMore: Boolean): UiData<T> {
			return UiData(data, isRefresh, hasMore)
		}

		/**
		 * 返回失败数据
		 */
		fun <T> error(): UiData<T> {
			return UiData(data = null, isRefresh = false, hasMore = true)
		}
	}
}