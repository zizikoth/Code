package com.memo.component.ui.clip

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.component.adapter.StringAdapter
import kotlinx.android.synthetic.main.activity_clip_padding.*

class ClipPaddingActivity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_clip_padding

	override fun initialize() {
		mRvList.run {
			layoutManager = LinearLayoutManager(mContext)
			adapter = StringAdapter().apply {
				data = arrayListOf(
					"1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
					"1", "1", "1", "1", "1", "1", "1", "1", "1", "1"
				)
			}
		}

	}
}