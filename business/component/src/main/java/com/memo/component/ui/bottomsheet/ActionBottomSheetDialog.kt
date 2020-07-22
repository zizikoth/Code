package com.memo.component.ui.bottomsheet

import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.memo.component.R
import com.memo.core.tool.adapter.BaseRecyclerAdapter
import com.memo.core.tool.dialog.dialog.BaseBottomSheetDialog
import com.memo.core.tool.ext.dimen
import kotlinx.android.synthetic.main.dialog_action_bottom_sheet.view.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-06 16:46
 */
class ActionBottomSheetDialog : BaseBottomSheetDialog() {

	private val mAdapter by lazy {
		object : BaseRecyclerAdapter<String>(R.layout.item_multi_text) {
			override fun convert(holder: BaseViewHolder, item: String) {
				holder.setText(R.id.tv_title, item)
			}
		}
	}

	override fun bindLayoutRes(): Int = R.layout.dialog_action_bottom_sheet

	override fun bindBehavior(behavior: BottomSheetBehavior<View>) {
		behavior.peekHeight = dimen(R.dimen.dp400).toInt()
	}

	override fun bindParams(params: WindowManager.LayoutParams) {
		params.dimAmount = 0.2f
	}

	override fun bindAnimStyle(): Int = R.style.BottomSlideDialogAnim

	override fun initialize() {
		contentView.mRvList.run {
			layoutManager = LinearLayoutManager(context)
			adapter = mAdapter
		}
	}

	fun setData(data: MutableList<String>): ActionBottomSheetDialog {
		mAdapter.data = data
		return this
	}


	fun setOnItemClickListener(onItemClick: (dialog: BaseBottomSheetDialog, position: Int, data: String) -> Unit): ActionBottomSheetDialog {
		mAdapter.setOnItemClickListener { _, _, position ->
			onItemClick(this, position, mAdapter.data[position])
			dismissAllowingStateLoss()
		}
		return this
	}

}