package com.memo.core.tool.adapter.rv

import android.view.View
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.memo.core.tool.helper.ClickHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-08-14 09:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseRVBindingAdapter<T, BD : ViewDataBinding>(layoutResId: Int) :
	BaseQuickAdapter<T, BaseDataBindingHolder<BD>>(layoutResId) {

	override fun convert(holder: BaseDataBindingHolder<BD>, item: T) {
		holder.dataBinding?.apply {
			converts(holder, item)
			executePendingBindings()
		}
	}

	override fun setOnItemClick(v: View, position: Int) {
		if (ClickHelper.isNotFastClick)
			super.setOnItemClick(v, position)
	}

	override fun setOnItemLongClick(v: View, position: Int): Boolean {
		return if (ClickHelper.isNotFastLongClick)
			super.setOnItemLongClick(v, position)
		else
			false
	}

	override fun setOnItemChildClick(v: View, position: Int) {
		if (ClickHelper.isNotFastClick)
			super.setOnItemChildClick(v, position)
	}

	override fun setOnItemChildLongClick(v: View, position: Int): Boolean {
		return if (ClickHelper.isNotFastLongClick)
			super.setOnItemChildLongClick(v, position)
		else
			false
	}

	/**
	 * 直接进行数据负值 无需其他操作
	 * @param holder BaseDataBindingHolder<BD>
	 * @param item 数据源
	 */
	abstract fun converts(holder: BaseDataBindingHolder<BD>, item: T)

}