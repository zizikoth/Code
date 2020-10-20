package com.memo.home.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.memo.base.entity.remote.BannerResponse
import com.memo.base.manager.router.RouterManager
import com.memo.base.ui.mvvm.BaseVmFragment
import com.memo.core.tool.ext.finish
import com.memo.core.tool.ext.marginStatusBar
import com.memo.core.tool.ext.onRefreshAndLoadListener
import com.memo.core.tool.ext.paddingStatusBar
import com.memo.home.R
import com.memo.home.ui.adapter.ArticleAdapter
import com.memo.home.ui.adapter.BannerAdapter
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.abs

/**
 * title:首页
 * describe:
 *
 * @author memo
 * @date 2020-04-29 16:35
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeFragment : BaseVmFragment<HomeViewModel>() {

	private val mAdapter by lazy { ArticleAdapter() }

	private lateinit var mBannerView: BannerViewPager<BannerResponse, BannerAdapter.BannerViewHolder>

	override fun bindLayoutRes(): Int = R.layout.fragment_home

	override fun bindContentView(): View? = mContent

	override fun initData(argument: Bundle?) {}

	override fun initView() {
		// 设置ToolBar距顶
		mToolBar.paddingStatusBar()
		mFlSearch.marginStatusBar()

		// 轮播图
		mBannerView = mRootView.findViewById(R.id.mBanner)
		mBannerView.run {
			adapter = BannerAdapter()
			setOnPageClickListener {
				val article = mBannerView.data[it]
				RouterManager.startArticleDetail(article.id, article.title, article.url)
			}
			create()
		}

		// RecycleView
		mRvList.run {
			layoutManager = LinearLayoutManager(mContext)
			adapter = mAdapter
		}

	}

	override fun initListener() {
		// 设置AppBar的透明度
		mAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
			mToolBar.alpha = abs(offset).toFloat() / appBar.totalScrollRange
		})
		// 标题栏点击标题滑动到顶部
		mTitleView.setOnTitleClickListener {
			// 但顶部完全显示 滑动到顶部
			if (mToolBar.alpha == 1f) {
				val behavior = (mAppBar.layoutParams as CoordinatorLayout.LayoutParams).behavior
				if (behavior is AppBarLayout.Behavior) {
					behavior.topAndBottomOffset = 0
				}
				mRvList.scrollToPosition(0)
			}
		}
		// 刷新
		mRefreshLayout.onRefreshAndLoadListener({
			mViewModel.getHomeData(true)
		}, {
			mViewModel.getHomeData(false)
		})
		// 条目点击
		mAdapter.setOnItemClickListener { _, _, position ->
			val article = mAdapter.data[position]
			RouterManager.startArticleDetail(article.id, article.title, article.link)
		}
	}

	override fun initObserver() {
		mViewModel.homeLiveData.observe(this, { data ->
			data.data?.let {
				// 轮播图
				it.banner?.let { mBannerView.refreshData(it) }
				// 文章
				if (data.isRefresh) {
					mAdapter.setList(it.articles)
				} else {
					mAdapter.addData(it.articles)
				}
			}
			mRefreshLayout.finish(data.hasMore)
		})
	}

	override fun start() {
		mViewModel.getHomeData(true)
	}

}
