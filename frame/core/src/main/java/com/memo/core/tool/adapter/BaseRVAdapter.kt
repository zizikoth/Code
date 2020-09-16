package com.memo.core.tool.adapter

import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.util.set
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.memo.core.tool.helper.ClickHelper

/**
 * title:对BaseQuickAdapter的改造
 * describe:
 *
 * @author zhou
 * @date 2019-07-04 14:55
 */
abstract class BaseRVAdapter<T>(@LayoutRes layoutResInt: Int) :
	BaseQuickAdapter<T, BaseViewHolder>(layoutResInt) {

	/*** 用户存储子RecyclerView的状态 ***/
	protected val mStateCache by lazy { SparseArray<Parcelable?>() }

	/**
	 * 设置数据并且恢复状态
	 * @param data 数据源
	 * @param state 状态数据
	 */
	fun setData(data: MutableList<T>, state: Parcelable?) {
		(this.recyclerView.layoutManager as LinearLayoutManager?)?.onRestoreInstanceState(state)
		setList(data)
	}

	/**
	 * 在界面回收的时候进行保存子RecyclerView的状态
	 * 对于其他自定义控件 建议进行自定义的保存状态
	 * @param holder ViewHolder
	 */
	override fun onViewRecycled(holder: BaseViewHolder) {
		bindSaveStateChildRecyclerView(holder)?.let {
			val position = holder.adapterPosition
			val layoutManager: RecyclerView.LayoutManager? = it.layoutManager
			mStateCache[position] = layoutManager?.onSaveInstanceState()
		}
		super.onViewRecycled(holder)
	}

	/**
	 * 绑定子RecyclerView
	 * @param holder ViewHolder
	 * @return RecyclerView?
	 */
	protected open fun bindSaveStateChildRecyclerView(holder: BaseViewHolder): RecyclerView? = null


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


}