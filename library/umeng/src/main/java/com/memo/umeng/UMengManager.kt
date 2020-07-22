package com.memo.umeng

import com.memo.core.BuildConfig
import com.memo.core.tool.app.BaseApp
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-14 16:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class UMengManager {

	// 文档地址 https://developer.umeng.com/docs/66632/detail/66639
	// QQ   https://connect.qq.com/
	// 微信 https://open.weixin.qq.com/
	// 微博 https://open.weibo.com/
	// 支付宝 https://open.alipay.com/platform/home.htm


	private object Holder {
		val instance = UMengManager()
	}

	companion object {
		fun get() = Holder.instance
	}

	/**
	 * 初始化
	 * @param umengKey 友盟的key
	 */
	fun init(umengKey: String): UMengManager {
		UMConfigure.init(BaseApp.app.applicationContext, umengKey, "umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
		UMConfigure.setLogEnabled(BuildConfig.DEBUG)
		return this
	}

	/**
	 * 注册微信
	 * @param appId 例子：wxdc1e388c3822c80b
	 * @param appKey 例子：3baf1193c85774b3fd9d18447d76cab0
	 */
	fun registerWeChat(appId: String, appKey: String): UMengManager {
		PlatformConfig.setWeixin(appId, appKey)
		return this
	}

	/**
	 * 注册QQ
	 * @param appId 例子：100424468
	 * @param appKey 例子：c7394704798a158208a74ab60104f0ba
	 */
	fun registerQQ(appId: String, appKey: String): UMengManager {
		PlatformConfig.setQQZone(appId, appKey)
		return this
	}

	/**
	 * 注册新浪微博
	 * @param appId 例子：3921700954
	 * @param appKey 例子：04b48b094faeb16683c32669824ebdad
	 * @param callbackUrl OAuth2.0客户端默认回调页：https://api.weibo.com/oauth2/default.html
	 */
	fun registerWeiBo(appId: String, appKey: String, callbackUrl: String = "https://api.weibo.com/oauth2/default.html"): UMengManager {
		PlatformConfig.setSinaWeibo(appId, appKey, callbackUrl)
		return this
	}

}