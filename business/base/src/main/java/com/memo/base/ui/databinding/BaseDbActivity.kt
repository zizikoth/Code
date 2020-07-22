package com.memo.base.ui.databinding

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.memo.base.R
import com.memo.core.tool.dialog.dialog.LoadingDialog
import com.memo.core.tool.helper.KeyboardHelper
import com.memo.core.tool.helper.OOMHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-07-22 16:17
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseDbActivity<DB : ViewDataBinding> : AppCompatActivity() {

	/*** Context ***/
	private val mContext: BaseDbActivity<DB> by lazy { this }

	/*** 生命周期 ***/
	private val mLifecycleOwner: LifecycleOwner by lazy { this }

	/*** 加载弹窗 ***/
	protected val mLoadDialog: LoadingDialog by lazy { LoadingDialog() }

	/*** 是否点击空白处隐藏软键盘 ***/
	protected open fun clickBlank2HideKeyboard(): Boolean = true

	/*** 是否透明状态栏 ***/
	protected open fun transparentStatusBar(): Boolean = false

	/*** 设置状态栏颜色 ***/
	@ColorInt
	protected open fun statusBarColor(): Int = Color.WHITE

	/**
	 * 当前Activity是否始终是竖屏 不会发生屏幕变化
	 * false 一般配合 android:configChanges="keyboardHidden|orientation|screenSize"
	 */
	protected open fun alwaysPortrait(): Boolean = true

	protected lateinit var mBinding: DB

	override fun onCreate(savedInstanceState: Bundle?) {
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.activity_fade_hide)
		super.onCreate(savedInstanceState)
		initDataBinding()
		initConfig()
		initialize()
	}

	private fun initDataBinding() {
		mBinding = DataBindingUtil.setContentView(mContext, bindLayoutRes())
		mBinding.lifecycleOwner = mLifecycleOwner
	}

	private fun initConfig() {
		if (transparentStatusBar()) {
			// 状态栏透明
			BarUtils.setStatusBarColor(this, Color.TRANSPARENT, true)
		} else {
			// 状态栏颜色
			BarUtils.setStatusBarLightMode(this, false)
			BarUtils.setStatusBarColor(this, statusBarColor(), true)
			BarUtils.addMarginTopEqualStatusBarHeight(mBinding.root)
		}
		// 设置是否始终竖屏
		if (alwaysPortrait()) {
			ScreenUtils.setPortrait(this)
		}
	}

	/*** 分发点击事件 用来隐藏软键盘 ***/
	override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
		if (clickBlank2HideKeyboard()) {
			KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
		}
		return super.dispatchTouchEvent(ev)
	}

	/*** 显示加载弹窗 ***/
	fun showLoading(tip: String = "加载中") {
		mLoadDialog.showLoading(supportFragmentManager, tip)
	}

	/*** 隐藏加载弹窗 ***/
	fun hideLoading() {
		mLoadDialog.hideLoading()
	}

	override fun finish() {
		super.finish()
		overridePendingTransition(R.anim.activity_fade_show, R.anim.slide_out_to_right)
	}

	override fun onDestroy() {
		super.onDestroy()
		// 销毁软键盘
		KeyboardHelper.onDestroy(mContext)
		// 清除所有的图片内存占用
		OOMHelper.onDestroy(mBinding.root)
	}

	/**
	 * 绑定布局文件
	 */
	@LayoutRes
	abstract fun bindLayoutRes(): Int

	/**
	 * 进行初始化操作
	 */
	abstract fun initialize()
}