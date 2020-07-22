package com.memo.home.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import com.memo.base.entity.local.UiData
import com.memo.base.entity.remote.HomeZipResponse
import com.memo.base.ui.vm.BaseViewModel
import com.memo.home.api.HomeRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:38
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeViewModel : BaseViewModel() {

	private var page = 0

	val homeLiveData: MutableLiveData<UiData<HomeZipResponse>> = MutableLiveData()

	fun getHomeData(isRefresh: Boolean) {
		page = if (isRefresh) 0 else page
		request(
			request = { HomeRepository.getHomeData(page) },
			onSuccess = {
				homeLiveData.postValue(UiData.success(it, page == 0, it.hasMore))
				page++
			},
			onError = {
				homeLiveData.postValue(UiData.error())
				if (page > 0) page--
			},
			showLoading = false
		)
	}
}