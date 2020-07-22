package com.memo.base.entity.remote

/**
 * title:网络请求基础类
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BaseResponse<T>(val data: T, val errorCode: Int = 0, val errorMsg: String = "") {
	fun isSuccess() = errorCode == 0
}
