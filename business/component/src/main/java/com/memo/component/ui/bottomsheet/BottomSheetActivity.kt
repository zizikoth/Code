package com.memo.component.ui.bottomsheet

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.adapter.rv.BaseRVAdapter
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : BaseActivity() {

	private val data = arrayListOf(
		"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡",
		"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡",
		"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡",
		"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡",
		"床前明月光", "疑是地上霜", "举头望明月", "低头思故乡"
	)

	private val mListAdapter by lazy {
		object : BaseRVAdapter<String>(R.layout.layout_item_looper_text) {
			override fun convert(holder: BaseViewHolder, item: String) {
				(holder.itemView as TextView).text = item
			}
		}
	}

	private val mDataAdapter by lazy {
		object : BaseRVAdapter<String>(R.layout.layout_item_looper_text) {
			override fun convert(holder: BaseViewHolder, item: String) {
				(holder.itemView as TextView).text = item
			}
		}
	}

	override fun bindLayoutRes(): Int = R.layout.activity_bottom_sheet

	override fun initialize() {
		val behavior = BottomSheetBehavior.from(mLlBottomSheet)
		mRvList.run {
			adapter = mListAdapter
			layoutManager = LinearLayoutManager(mContext)
			mListAdapter.data = data
			isNestedScrollingEnabled = false
			addOnScrollListener(object : RecyclerView.OnScrollListener() {
				override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
					if (behavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
						behavior.state = BottomSheetBehavior.STATE_COLLAPSED
					}
				}
			})
		}
		mRvData.run {
			adapter = mDataAdapter
			layoutManager = LinearLayoutManager(mContext)
			mDataAdapter.data = data
		}
	}

}
