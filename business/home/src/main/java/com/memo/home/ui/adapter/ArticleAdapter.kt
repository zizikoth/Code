package com.memo.home.ui.adapter

import android.graphics.Color
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.daimajia.swipe.SwipeLayout
import com.memo.base.R
import com.memo.base.entity.remote.ArticleResponse
import com.memo.base.utils.IconHelper
import com.memo.core.tool.adapter.rv.BaseRVAdapter
import com.memo.core.tool.ext.color
import com.memo.core.tool.ext.fromHtml
import com.memo.core.tool.helper.GlideHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 10:30
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ArticleAdapter(private val enableSwipe: Boolean = false) : BaseRVAdapter<ArticleResponse>(R.layout.layout_item_article) {

	init {
		addChildClickViewIds(R.id.mClContent, R.id.mFlDelete)
	}

	override fun convert(holder: BaseViewHolder, item: ArticleResponse) {
		val showPic = item.envelopePic.isNotEmpty()
		holder
			.setImageResource(R.id.mIvIcon, IconHelper.randomIcon(item.chapterId))
			.setText(
				R.id.mTvName, if (item.author.isEmpty()) {
					if (item.shareUser.isEmpty()) {
						"匿名"
					} else {
						"分享自：${item.shareUser}"
					}
				} else {
					"作者：${item.author}"
				}
			)
			.setText(R.id.mTvTitle, item.title.fromHtml())
			.setText(R.id.mTvDesc, item.desc.fromHtml())
			.setGone(R.id.mTvDesc, item.desc.isNotEmpty())
			.setText(
				R.id.mTvChapter, if (item.superChapterName.isNotEmpty()) {
					"${item.superChapterName} · ${item.chapterName}"
				} else {
					item.chapterName
				}
			)
			.setText(R.id.mTvTime, item.niceDate)
			.setGone(R.id.mIvPic, showPic)
			.setGone(R.id.mTvTop, item.isTop)
			.setBackgroundColor(
				R.id.mClContent, if (item.isTop) {
					color(R.color.color_bg_top)
				} else {
					Color.WHITE
				}
			)
		holder.getView<SwipeLayout>(R.id.mSwipeLayout).isSwipeEnabled = enableSwipe
		if (showPic) {
			GlideHelper.loadImage(item.envelopePic, holder.getView(R.id.mIvPic))
		}
	}
}