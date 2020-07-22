package com.memo.base.ui.databinding

import android.content.Intent
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.core.LoadService
import com.memo.base.api.ApiCode
import com.memo.base.status.callback.NetErrorCallback
import com.memo.base.status.callback.ServerErrorCallback
import com.memo.base.ui.vm.BaseViewModel
import com.memo.base.utils.ext.getViewModelClass
import com.memo.base.utils.ext.loadSir

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-07-22 16:36
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseDbActivity<DB>() {

	protected lateinit var mViewModel: VM

	protected var mLoadSir: LoadService<*>? = null

	override fun initialize() {
		initBase()
		initData(intent)
		initView()
		initListener()
		initObserver()
		start()
	}

	private fun initBase() {
		mLoadSir = loadSir(bindContentView()) { start() }
		mViewModel = ViewModelProvider(this).get(getViewModelClass(this) as Class<VM>)
		mViewModel.loadingEvent.observe(this, Observer {
			if (it.isNotEmpty()) showLoading(it) else hideLoading()
		})
		mViewModel.uiStateChangeEvent.observe(this, Observer {
			if (it.isFirstLoad) {
				when (it.code) {
					ApiCode.ServerErrorCode -> mLoadSir?.showCallback(ServerErrorCallback::class.java)
					ApiCode.NetworkErrorCode -> mLoadSir?.showCallback(NetErrorCallback::class.java)
				}
			} else if (it.code == ApiCode.Success) {
				mLoadSir?.showSuccess()
			}
		})
	}

	/*** 绑定加载失败后的可点击父容器 ***/
	open fun bindContentView(): View? {
		return null
	}

	/*** 初始化数据 ***/
	abstract fun initData(intent: Intent)

	/*** 初始化控件 ***/
	abstract fun initView()

	/*** 初始化监听 ***/
	abstract fun initListener()

	/*** 初始化数据回调 ***/
	abstract fun initObserver()

	/*** 开始数据请求 ***/
	abstract fun start()

}