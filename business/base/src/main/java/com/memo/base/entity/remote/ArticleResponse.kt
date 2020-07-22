package com.memo.base.entity.remote


/**
 * title:文章列表中的详情数据
 * describe:
 *
 * @author memo
 * @date 2020-04-29 15:29
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class ArticleResponse(
	var apkLink: String,
	var author: String, //作者
	var chapterId: Int,
	var chapterName: String,
	var collect: Boolean, //是否收藏
	var courseId: Int,
	var desc: String,
	var envelopePic: String,
	var fresh: Boolean,
	var id: Int,
	var link: String,
	var niceDate: String,
	var origin: String,
	var prefix: String,
	var projectLink: String,
	var publishTime: Long,
	var superChapterId: Int,
	var superChapterName: String,
	var shareUser: String,
	var tags: ArrayList<TagsResponse>,
	var title: String,
	var type: Int,
	var userId: Int,
	var visible: Int,
	var zan: Int,
	var isTop: Boolean = false
)