package com.memo.component.ui.share

import android.annotation.SuppressLint
import android.transition.Slide
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.component.entity.ShareEntity
import com.memo.core.tool.helper.GlideHelper
import kotlinx.android.synthetic.main.activity_share_element.*

/**
 * title:共享元素
 * describe:
 *
 * @author memo
 * @date 2020-07-30 16:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ShareElementActivity : BaseActivity() {

	override fun transparentStatusBar(): Boolean = true

	/*** 绑定布局id ***/
	override fun bindLayoutRes(): Int = R.layout.activity_share_element

	/*** 进行初始化操作 ***/
	@SuppressLint("NewApi")
	override fun initialize() {
		window.enterTransition = Slide().setDuration(1000)

		intent.getParcelableExtra<ShareEntity>("data")?.let {
			GlideHelper.loadImage(it.image, mIvCover)
			mTvTitle.text = it.title
			mTvContent.text = it.content
		}
	}
}