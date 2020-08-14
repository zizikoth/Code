package com.memo.component.ui.share

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.component.entity.ShareEntity
import com.memo.core.tool.ext.forEach
import com.memo.core.tool.ext.string
import kotlinx.android.synthetic.main.activity_share_element_list.*

/**
 * title:共享元素列表
 * describe:
 *
 * @author memo
 * @date 2020-07-30 17:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ShareElementListActivity : BaseActivity() {

	private val list = arrayListOf<ShareEntity>().apply { 10.forEach { add(ShareEntity()) } }

	private val mAdapter = ShareAdapter()

	/*** 绑定布局id ***/
	override fun bindLayoutRes(): Int = R.layout.activity_share_element_list

	/*** 进行初始化操作 ***/
	@SuppressLint("NewApi")
	override fun initialize() {
		mRvList.run {
			layoutManager = LinearLayoutManager(mContext)
			mAdapter.setList(list)
			adapter = mAdapter
		}

		mAdapter.setOnItemClickListener { _, _, position ->
			val intent = Intent(mContext, ShareElementActivity::class.java).apply {
				putExtra("data", mAdapter.data[position])
			}
			val pairCover: Pair<View, String> =
				Pair(mAdapter.getViewByPosition(position, R.id.mIvCover)!!, string(R.string.ShareElementCover))
			val pairTitle: Pair<View, String> =
				Pair(mAdapter.getViewByPosition(position, R.id.mTvTitle)!!, string(R.string.ShareElementTitle))
			val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, pairCover, pairTitle)
			startActivity(intent, options.toBundle())
		}
	}
}