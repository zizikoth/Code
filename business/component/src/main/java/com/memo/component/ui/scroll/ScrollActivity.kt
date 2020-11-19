package com.memo.component.ui.scroll

import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.ext.firstLineIndent
import kotlinx.android.synthetic.main.activity_scroll.*

class ScrollActivity : BaseActivity() {

	/*** 绑定布局id ***/
	override fun bindLayoutRes(): Int = R.layout.activity_scroll

	/*** 进行初始化操作 ***/
	override fun initialize() {
		val text = "以上两种方法，不同设备都会有这样那样的问题，总是不完美。后来我发现最完美的莫过于隐藏掉原文的头两个字符，达到缩进的错觉"
		mTvContent.text = text.firstLineIndent()
	}
}