package com.memo.core.widget.ninegridview

import androidx.annotation.DrawableRes
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.memo.core.R
import com.memo.core.tool.adapter.rv.BaseRVAdapter
import com.memo.core.tool.helper.GlideHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-05 15:59
 */
class NineGridViewAdapter : BaseRVAdapter<Any>(R.layout.item_nine_grid_view) {


	private var mAddDrawableRes: Int = R.drawable.ic_pic_add
	private var mDelDrawableRes: Int = R.drawable.ic_pic_del

	init {
		addChildClickViewIds(R.id.mIvPic, R.id.mIvDel)
	}

	override fun convert(holder: BaseViewHolder, item: Any) {
		GlideHelper.loadImage(item, holder.getView(R.id.mIvPic))

		holder.setGone(R.id.mIvDel, item != mAddDrawableRes)
			.setImageResource(R.id.mIvDel, mDelDrawableRes)
	}

	/**
	 * 设置图片资源
	 * @param addRes Int 添加图片资源
	 * @param delRes Int 删除图片资源
	 */
	fun setDrawableRes(@DrawableRes addRes: Int, @DrawableRes delRes: Int) {
		mAddDrawableRes = addRes
		mDelDrawableRes = delRes
	}
}