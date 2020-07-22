package com.memo.component.launcher

import com.memo.base.manager.init.InitManager
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.dir.LocalDir

class MainActivity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_main

	override fun initialize() {
		InitManager.initLater()
		LocalDir.createLocalDir()

		supportFragmentManager.beginTransaction()
			.add(R.id.mContainer, ComponentFragment())
			.commit()
	}

}
