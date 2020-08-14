package com.memo.component.ui.share

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.memo.component.R
import com.memo.component.entity.ShareEntity
import com.memo.core.tool.helper.GlideHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-07-30 17:07
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ShareAdapter : BaseQuickAdapter<ShareEntity, BaseViewHolder>(R.layout.layout_item_share_element) {

	override fun convert(holder: BaseViewHolder, item: ShareEntity) {
		GlideHelper.loadImage(item.image, holder.getView(R.id.mIvCover))
		holder.setText(R.id.mTvTitle, item.title)
	}
}