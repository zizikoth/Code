package com.memo.base.ui.mvvm

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.BarUtils
import com.memo.base.R
import com.memo.core.tool.dialog.dialog.LoadingDialog
import com.memo.core.tool.ext.inflaterView
import com.memo.core.tool.helper.KeyboardHelper
import com.memo.core.tool.helper.OOMHelper

/**
 * title:基础的Activity
 * tip:
 *
 * @author zhou
 * @date 2018-11-14 上午9:54
 */
abstract class BaseActivity : AppCompatActivity() {

	/*** 根布局 ***/
	protected lateinit var mRootView: View

	/*** Context ***/
	protected val mContext: BaseActivity by lazy { this }

	/*** LifecycleOwner ***/
	protected val mLifecycleOwner: LifecycleOwner by lazy { this }

	/*** 加载弹窗 ***/
	protected val mLoadDialog: LoadingDialog by lazy { LoadingDialog() }

	/*** 是否点击空白处隐藏软键盘 ***/
	protected open fun clickBlank2HideKeyboard(): Boolean = true

	/*** 是否透明状态栏 ***/
	protected open fun transparentStatusBar(): Boolean = false

	/*** 设置状态栏颜色 ***/
	@ColorInt
	protected open fun statusBarColor(): Int = Color.WHITE

	override fun onCreate(savedInstanceState: Bundle?) {
		overridePendingTransition(R.anim.slide_in_from_right, R.anim.activity_fade_hide)
		super.onCreate(savedInstanceState)
		if (bindLayoutRes() != -1) {
			mRootView = inflaterView(bindLayoutRes())
			setContentView(mRootView)
		}
		baseInit()
		initialize()
	}

	/*** 基础的初始化操作 ***/
	protected open fun baseInit() {
		if (transparentStatusBar()) {
			// 状态栏透明
			BarUtils.setStatusBarColor(this, Color.TRANSPARENT, true)
		} else {
			// 状态栏颜色
			BarUtils.setStatusBarLightMode(this, false)
			BarUtils.setStatusBarColor(this, statusBarColor(), true)
			BarUtils.addMarginTopEqualStatusBarHeight(mRootView)
		}
	}

	/*** 绑定布局id ***/
	@LayoutRes
	protected abstract fun bindLayoutRes(): Int

	/*** 进行初始化操作 ***/
	protected abstract fun initialize()

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
		OOMHelper.onDestroy(mRootView)
	}
}