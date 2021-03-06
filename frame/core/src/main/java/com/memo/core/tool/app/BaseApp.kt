package com.memo.core.tool.app

import android.app.Application
import androidx.multidex.MultiDexApplication

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-26 14:47
 */
open class BaseApp : MultiDexApplication() {

	companion object {
		lateinit var app: Application
	}

	override fun onCreate() {
		super.onCreate()
		app = this
	}

}