package com.memo.core.tool.dialog.dialog

import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
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
 * @date 2020-04-28 13:05
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BottomGridDialog(private val mData: ArrayList<GridItem> = arrayListOf()) :
	BaseDialog() {

	class GridItem(var drawableSrc: Int, var name: String)

	private val mAdapter: BaseRVAdapter<GridItem> =
		object : BaseRVAdapter<GridItem>(R.layout.dialog_bottom_grid_item) {
			override fun convert(holder: BaseViewHolder, item: GridItem) {
				val itemView: TextView = holder.itemView as TextView
				itemView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, item.drawableSrc, 0, 0)
				itemView.text = item.name
			}
		}


	override fun bindLayout(): Int = R.layout.dialog_bottom_list

	override fun bindView(root: View) {
		root.mRvContent.layoutManager = GridLayoutManager(context, 3)
		root.mRvContent.adapter = mAdapter
		root.mRvContent.setHasFixedSize(true)
		(root.mRvContent.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
		mAdapter.setList(mData)
		root.mTvClose.onClick { dismiss() }
	}

	override fun bindParams(params: WindowManager.LayoutParams) {
		params.dimAmount = 0.2f
		params.gravity = Gravity.BOTTOM
		params.width = WindowManager.LayoutParams.MATCH_PARENT
	}

	override fun bindAnimStyle(): Int = R.style.BottomSlideDialogAnim

	fun setOnItemClickListener(onItemClick: (Int, GridItem) -> Unit): BottomGridDialog {
		mAdapter.setOnItemClickListener { _, _, position ->
			onItemClick.invoke(position, mAdapter.data[position])
			dismiss()
		}
		return this
	}

}