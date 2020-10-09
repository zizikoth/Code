package com.memo.base.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.memo.base.api.ApiCode
import com.memo.base.api.ApiException
import com.memo.base.api.ExceptionHandler
import com.memo.base.entity.local.UiChangeState
import com.memo.base.entity.remote.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:47
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
open class BaseViewModel : ViewModel() {

	private var isFirstLoad: Boolean = true

	val loadingEvent: MutableLiveData<String> = MutableLiveData()

	val uiStateChangeEvent: MutableLiveData<UiChangeState> = MutableLiveData()

	/**
	 * 网络请求工具
	 * @param request 请求体
	 * @param onSuccess 请求成功
	 * @param onError 请求失败
	 * @param showLoading 显示loading
	 * @param loadingMsg loading提示
	 */
	fun <T> request(
		request: suspend () -> BaseResponse<T>,
		onSuccess: (T) -> Unit,
		onError: () -> Unit,
		showLoading: Boolean = true,
		loadingMsg: String = "加载中"
	) {
		viewModelScope.launch(Dispatchers.Main) {
			// 加载弹窗
			if (showLoading) loadingEvent.postValue(loadingMsg)
			try {
				// 子线程中进行网路请求
				val data = withContext(Dispatchers.IO) { request() }
				if (data.isSuccess()) {
					isFirstLoad = false
					onSuccess.invoke(data.data)
					uiStateChangeEvent.postValue(UiChangeState(isFirstLoad, ApiCode.Success))
				} else {
					uiStateChangeEvent.postValue(
						UiChangeState(isFirstLoad, ExceptionHandler.handleException(ApiException(data.errorCode, data.errorMsg)))
					)
					onError.invoke()
				}
			} catch (e: Exception) {
				uiStateChangeEvent.postValue(UiChangeState(isFirstLoad, ExceptionHandler.handleException(e)))
				onError.invoke()
			} finally {
				loadingEvent.postValue("")
			}
		}
	}

	fun <T> requestNoCheck(block: suspend () -> BaseResponse<T>) {
		viewModelScope.launch {
			try {
				block.invoke()
			} catch (e: Exception) {
				LogUtils.eTag("Request-No-Check", "请求失败\n$e")
			}
		}
	}
}
