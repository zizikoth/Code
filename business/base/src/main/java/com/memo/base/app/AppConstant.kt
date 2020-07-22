package com.memo.base.app

/**
 * title:静态常量
 * describe: APP级别的
 *
 * @author memo
 * @date 2020-04-28 14:56
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object AppConstant {

	object AppKeys {
		const val DoraemonKitId = "3b05092ab9b67c4d77be1e3a7f740d37"
	}

	object Api {
		var BASE_URL =
			if (AppConfig.isDebug) "https://wanandroid.com/" else "https://wanandroid.com/"
	}

}