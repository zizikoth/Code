package com.memo.component.ui.vp2

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.component.ui.navigation.OneFragment
import com.memo.component.ui.navigation.ThreeFragment
import com.memo.component.ui.navigation.TwoFragment
import com.memo.core.tool.adapter.vp2.BaseFragmentPager2Adapter
import kotlinx.android.synthetic.main.activity_view_pager2.*

/**
 * title:ViewPager2
 * describe:
 * ViewPager2+Fragment已经帮你完成了Fragment的懒加载，只有在页面滑到之后Fragment才会进行加载
 * ViewPager则会进行预加载
 *
 * @author memo
 * @date 2020-09-24 16:38
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ViewPager2Activity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_view_pager2

	override fun initialize() {
		val fragments = arrayListOf<Fragment>(
			OneFragment(), TwoFragment(), ThreeFragment(),
			OneFragment(), TwoFragment(), ThreeFragment(),
			OneFragment(), TwoFragment(), ThreeFragment()
		)
		val titles = arrayListOf<String>()
		fragments.forEach { titles.add(it::class.java.simpleName) }
		val adapter = BaseFragmentPager2Adapter(mContext)
		adapter.setData(fragments)
		mVp2.adapter = adapter
		TabLayoutMediator(mTab, mVp2) { tab, position ->
			tab.text = titles[position]
		}.attach()
	}
}