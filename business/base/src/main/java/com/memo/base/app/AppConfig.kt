package com.memo.base.app

/**
 * title:配置参数
 * describe:APP级别的配置参数
 *
 * @author memo
 * @date 2020-04-28 14:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object AppConfig {
	/**
	 * 是否开启日志打印
	 */
	const val isOpenLog: Boolean = true

	/**
	 * 是否是测试版本
	 * 注意 如果不是测试 那么就是发布版本
	 */
	const val isDebug: Boolean = true

	/**
	 * 导航页更新的版本 对应更新导航页的app版本
	 */
	const val guideCode = 1000
}