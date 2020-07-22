package com.memo.base.ui.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.memo.core.tool.helper.OOMHelper

/**
 * title:基础的Fragment
 * describe:
 * 注意添加 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
 * 建议配合 BaseFragmentPagerAdapter 和 FragmentHelper来进行使用
 *
 * @author zhou
 * @date 2019-09-26 15:38
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseFragment : Fragment() {

	/*** 根布局 ***/
	protected lateinit var mRootView: View

	/*** 上下文Activity ***/
	protected val mContext by lazy { activity!! }

	/*** 标识 标识是否界面准备完毕 ***/
	private var isPrepared: Boolean = false

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(bindLayoutRes(), container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mRootView = view
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
		if (isPrepared) {
			onVisibleToUser()
		}
		super.onResume()
	}

	protected fun showLoading(tip: String = "加载中") {
		if (mContext is BaseActivity) {
			(mContext as BaseActivity).showLoading(tip)
		}
	}

	protected fun hideLoading() {
		if (mContext is BaseActivity) {
			(mContext as BaseActivity).hideLoading()
		}
	}

	/*** 绑定布局 ***/
	@LayoutRes
	protected abstract fun bindLayoutRes(): Int

	/*** 在界面可见的时候进行初始化 ***/
	protected abstract fun initialize()

	override fun onDestroyView() {
		super.onDestroyView()
		// 清除所有图片占用的内存
		OOMHelper.onDestroy(mRootView)
	}
}