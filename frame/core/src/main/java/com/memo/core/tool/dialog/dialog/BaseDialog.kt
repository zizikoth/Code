package com.memo.core.tool.dialog.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.fragment.app.FixDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils

/**
 * title: 基础配置Dialog
 * describe:
 *
 * @author memo
 * @date 2020-04-28 10:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseDialog : FixDialogFragment(), LifecycleObserver {

	protected var mRootView: View? = null

	/*** 绑定布局 ***/
	@LayoutRes
	abstract fun bindLayout(): Int

	/*** 设置子控件 ***/
	abstract fun bindView(root: View)

	/*** 绑定Dialog ***/
	protected open fun bindDialog(dialog: Dialog) {}

	/*** 设置窗体配置 ***/
	abstract fun bindParams(params: WindowManager.LayoutParams)

	@StyleRes
	abstract fun bindAnimStyle(): Int

	/*** 绑定Tag ***/
	open fun bindTag() = this::class.java.simpleName

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		mRootView = inflater.inflate(bindLayout(), container, false)
		bindView(mRootView!!)
		return mRootView!!
	}

	override fun onStart() {
		super.onStart()
		// 绑定生命周期
		if (context is LifecycleOwner) {
			(context as LifecycleOwner).lifecycle.addObserver(this)
			LogUtils.iTag("BaseDialog", "绑定生命周期")
		}
		// 设置点击不消失
		dialog?.let {
			it.setCancelable(false)
			it.setCanceledOnTouchOutside(false)
			it.setOnCancelListener(null)
			it.setOnDismissListener(null)
			bindDialog(it)
		}
		// 设置窗体配置
		dialog?.window?.let {
			it.setWindowAnimations(bindAnimStyle())
			it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
			val params = it.attributes
			bindParams(params)
			it.attributes = params
		}
	}

	fun show(manager: FragmentManager) {
		show(manager, bindTag())
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	private fun onLifecycleDestroy() {
		if (context is Activity) {
			val activity = context as Activity
			if (KeyboardUtils.isSoftInputVisible(activity)) {
				KeyboardUtils.hideSoftInput(activity)
			}
		}
		lifecycle.removeObserver(this)
		dismiss()
		LogUtils.iTag("BaseDialog", "解绑生命周期")
	}

}