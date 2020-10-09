package com.memo.component.ui.handler

import android.annotation.SuppressLint
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.lifecycle.WeakHandler
import kotlinx.android.synthetic.main.activity_share_element.*

@SuppressLint("SetTextI18n")
class HandlerActivity : BaseActivity() {

	private val mHandler: WeakHandler = WeakHandler(this)

	override fun bindLayoutRes(): Int = R.layout.activity_handler

	override fun initialize() {
		mHandler.postDelayed({
			mTvContent.text = "WeakHandler"
		}, 1000)
	}
}