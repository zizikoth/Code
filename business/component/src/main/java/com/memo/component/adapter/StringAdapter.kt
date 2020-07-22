package com.memo.component.adapter

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.memo.component.R
import com.memo.core.tool.adapter.BaseRecyclerAdapter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-06-16 10:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class StringAdapter : BaseRecyclerAdapter<String>(R.layout.dialog_bottom_list_item) {
	override fun convert(holder: BaseViewHolder, item: String) {
		holder.setText(R.id.mTvContent, item)
	}
}