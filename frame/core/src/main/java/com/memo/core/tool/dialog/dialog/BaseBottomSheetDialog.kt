package com.memo.core.tool.dialog.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.memo.core.R
import com.memo.core.tool.ext.dimen

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 16:13
 */
abstract class BaseBottomSheetDialog : BottomSheetDialogFragment(), LifecycleObserver {

	private val TAG by lazy { this.javaClass.simpleName }

	lateinit var mBehavior: BottomSheetBehavior<View>

	/*** 内容视图 ***/
	lateinit var contentView: View

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = super.onCreateDialog(savedInstanceState)
		contentView = LayoutInflater.from(context).inflate(bindLayoutRes(), null)
		dialog.setContentView(contentView)
		mBehavior = BottomSheetBehavior.from(contentView.parent as View)
		initConfig(dialog)
		if (context is LifecycleOwner) {
			(context as LifecycleOwner).lifecycle.addObserver(this)
		}
		return dialog
	}

	private fun initConfig(dialog: Dialog) {
		// 初始显示高度
		contentView.post {
			bindBehavior(mBehavior)
		}

		dialog.window?.apply {
			// 是否设置窗体背景透明
			val background = findViewById<View>(R.id.design_bottom_sheet)
			background.layoutParams.height = dimen(R.dimen.dp400).toInt()
			background?.setBackgroundResource(android.R.color.transparent)

			// 窗体参数配置
			val params = this.attributes
			bindParams(params)
			this.attributes = params

			// 弹出动画
			setWindowAnimations(bindAnimStyle())

		}
	}

	override fun onStart() {
		super.onStart()
		initialize()
	}

	/**
	 * 设置布局
	 */
	@LayoutRes
	abstract fun bindLayoutRes(): Int

	/**
	 * 行为设置
	 */
	abstract fun bindBehavior(behavior: BottomSheetBehavior<View>)

	/**
	 * 设置窗体配置
	 */
	abstract fun bindParams(params: WindowManager.LayoutParams)

	@StyleRes
	abstract fun bindAnimStyle(): Int

	/**
	 * 初始化方法
	 */
	abstract fun initialize()

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	private fun onLifecycleDestroy() {
		dismissAllowingStateLoss()
	}


	fun show(fragmentManager: FragmentManager) {
		try {
			val mDismissed = DialogFragment::class.java.getDeclaredField("mDismissed")
			mDismissed.isAccessible = true
			mDismissed.set(this, false)
			val mShownByMe = DialogFragment::class.java.getDeclaredField("mShownByMe")
			mShownByMe.isAccessible = true
			mShownByMe.set(this, true)
			fragmentManager.beginTransaction()
				.add(this, TAG)
				.commitAllowingStateLoss()
		} catch (e: Throwable) {
			LogUtils.eTag(TAG, e.toString())
		}
	}

	override fun dismiss() {
		super.dismissAllowingStateLoss()
	}

}