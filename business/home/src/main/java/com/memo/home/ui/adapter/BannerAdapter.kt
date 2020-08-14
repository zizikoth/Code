package com.memo.home.ui.adapter

import android.view.View
import android.widget.ImageView
import com.memo.base.entity.remote.BannerResponse
import com.memo.core.tool.helper.GlideHelper
import com.memo.home.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * title:轮播图适配器
 * describe:
 *
 * @author memo
 * @date 2020-04-30 11:15
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BannerAdapter : BaseBannerAdapter<BannerResponse, BannerAdapter.BannerViewHolder>() {

	override fun getLayoutId(viewType: Int): Int = R.layout.layout_item_banner

	override fun createViewHolder(itemView: View, viewType: Int): BannerViewHolder =
		BannerViewHolder(itemView)

	override fun onBind(holder: BannerViewHolder, data: BannerResponse, position: Int, pageSize: Int) {
		holder.bindData(data, position, pageSize)
	}

	inner class BannerViewHolder(itemView: View) : BaseViewHolder<BannerResponse>(itemView) {
		override fun bindData(data: BannerResponse, position: Int, pageSize: Int) {
			GlideHelper.loadImage(data.imagePath, (itemView as ImageView))
		}
	}


}

