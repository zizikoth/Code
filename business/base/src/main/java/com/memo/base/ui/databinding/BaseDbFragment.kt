package com.memo.base.ui.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.memo.core.tool.helper.OOMHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-07-22 16:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseDbFragment<DB : ViewDataBinding> : Fragment() {

	/*** 上下文Activity ***/
	protected val mContext by lazy { activity!! }

	/*** 生命周期 ***/
	protected val mLifecycleOwner: LifecycleOwner by lazy { this }

	/*** 标识 标识是否界面准备完毕 ***/
	private var isPrepared: Boolean = false

	protected lateinit var mBinding: DB

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		mBinding = DataBindingUtil.inflate(inflater, bindLayoutRes(), container, false)
		mBinding.lifecycleOwner = mLifecycleOwner
		return mBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		isPrepared = true
		onVisibleToUser()
	}

	private fun onVisibleToUser() {
		if (isPrepared && isResumed) {
			isPrepared = false
			initialize()
		}
	}

	override fun onResume() {
		super.onResume()
		if (isPrepared) onVisibleToUser()
	}

	protected fun showLoading(tip: String = "加载中") {
		if (mContext is BaseDbActivity<*>) {
			(mContext as BaseDbActivity<*>).showLoading(tip)
		}
	}

	protected fun hideLoading() {
		if (mContext is BaseDbActivity<*>) {
			(mContext as BaseDbActivity<*>).hideLoading()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		// 清除所有图片占用的内存
		OOMHelper.onDestroy(mBinding.root)
	}

	/*** 绑定界面 ***/
	@LayoutRes
	abstract fun bindLayoutRes(): Int

	/*** 初始化 ***/
	abstract fun initialize()

}