package com.memo.base.manager.router

import com.alibaba.android.arouter.launcher.ARouter

/**
 * title:路由管理类
 * describe:进行不同Module之间的路由跳转
 *
 * @author memo
 * @date 2020-04-28 14:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object RouterManager {

	/**
	 * 文章详情页面
	 * @param id    文章id
	 * @param title 文章标题
	 * @param url   文章网址
	 */
	fun startArticleDetail(id: Int, title: String, url: String) {
		ARouter.getInstance().build(RouterPath.ArticleDetail)
			.withInt("id", id)
			.withString("title", title)
			.withString("url", url)
			.navigation()
	}

}