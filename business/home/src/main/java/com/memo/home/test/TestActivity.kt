package com.memo.home.test

import com.memo.base.manager.init.InitManager
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.core.tool.helper.StatusBarHelper
import com.memo.home.R
import com.memo.home.ui.fragment.db.HomeDbFragment

class TestActivity : BaseActivity() {

	override fun transparentStatusBar(): Boolean = true

	override fun bindLayoutRes(): Int = R.layout.activity_test

	override fun initialize() {
		InitManager.initLater()
		// 状态栏字体黑暗模式
		StatusBarHelper.setStatusTextDarkMode(mContext)

		supportFragmentManager.beginTransaction()
			.add(R.id.mFlContainer, HomeDbFragment())
			.commit()

	}
}
