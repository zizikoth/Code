package com.memo.component.ui.fold

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.component.adapter.StringAdapter
import com.memo.core.tool.ext.forEach
import kotlinx.android.synthetic.main.activity_fold.*

class FoldActivity : BaseActivity() {

	override fun transparentStatusBar(): Boolean = true

	override fun bindLayoutRes(): Int = R.layout.activity_fold

	override fun initialize() {
		mRvList.run {
			layoutManager = LinearLayoutManager(mContext)
			adapter = StringAdapter().apply {
				val list = arrayListOf<String>()
				20.forEach { list.add(it.toString()) }
				setList(list)
			}
		}
	}
}