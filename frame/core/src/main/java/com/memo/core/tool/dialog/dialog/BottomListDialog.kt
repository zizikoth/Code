package com.memo.core.tool.dialog.dialog

import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.memo.core.R
import com.memo.core.tool.adapter.BaseRVAdapter
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.dialog_bottom_list.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-28 12:35
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BottomListDialog(private val data: ArrayList<String> = arrayListOf()) : BaseDialog() {

	/*** 适配器 ***/
	private val mAdapter: BaseRVAdapter<String> =
		object : BaseRVAdapter<String>(R.layout.dialog_bottom_list_item) {
			override fun convert(holder: BaseViewHolder, item: String) {
				holder.setText(R.id.mTvContent, item)
					.setGone(R.id.mLine, holder.adapterPosition != data.size)
			}
		}

	override fun bindLayout(): Int = R.layout.dialog_bottom_list

	override fun bindView(root: View) {
		root.mTvClose.onClick { dismiss() }
		root.mRvContent.layoutManager = LinearLayoutManager(context)
		root.mRvContent.adapter = mAdapter
		root.mRvContent.setHasFixedSize(true)
		(root.mRvContent.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
		mAdapter.setList(data)
	}

	override fun bindParams(params: WindowManager.LayoutParams) {
		params.dimAmount = 0.2f
		params.gravity = Gravity.BOTTOM
		params.width = WindowManager.LayoutParams.MATCH_PARENT
	}

	override fun bindAnimStyle(): Int = R.style.BottomSlideDialogAnim

	/**
	 * 设置数据源
	 */
	fun setData(data: ArrayList<String>): BottomListDialog {
		mAdapter.setList(data)
		return this
	}

	/**
	 * 在末尾添加一个数据
	 */
	fun addData(data: String): BottomListDialog {
		mAdapter.addData(data)
		return this
	}

	/**
	 * 可以在中间插入一个数据
	 */
	fun addData(position: Int, data: String): BottomListDialog {
		if (position < mAdapter.data.size) {
			mAdapter.addData(position, data)
		}
		return this
	}

	/**
	 * 删除一个数据
	 */
	fun removeData(position: Int): BottomListDialog {
		if (position < mAdapter.data.size) {
			mAdapter.removeAt(position)
		}
		return this
	}

	/**
	 * 更新某一项的数据
	 */
	fun updateData(position: Int, data: String): BottomListDialog {
		if (position < mAdapter.data.size) {
			mAdapter.data[position] = data
			mAdapter.notifyItemChanged(position)
		}
		return this
	}

	/**
	 * 设置点击监听
	 */
	fun setOnItemClickListener(method: (position: Int, item: String) -> Unit): BottomListDialog {
		mAdapter.setOnItemClickListener { _, _, position ->
			method.invoke(position, mAdapter.data[position])
			dismiss()
		}
		return this
	}
}