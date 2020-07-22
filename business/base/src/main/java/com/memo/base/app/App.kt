package com.memo.base.app

import com.memo.base.manager.init.InitManager
import com.memo.core.tool.app.BaseApp

/**
 * title:App
 * describe:
 *
 * @author memo
 * @date 2020-04-28 14:52
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class App : BaseApp() {

	override fun onCreate() {
		super.onCreate()
		InitManager.initApp(this)
	}
}