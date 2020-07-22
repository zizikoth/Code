package com.memo.base.entity.remote

/**
 * title:首页轮播图的数据
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:29
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class BannerResponse(
	var desc: String = "",
	var id: Int = 0,
	var imagePath: String = "",
	var isVisible: Int = 0,
	var order: Int = 0,
	var title: String = "",
	var type: Int = 0,
	var url: String = ""
)