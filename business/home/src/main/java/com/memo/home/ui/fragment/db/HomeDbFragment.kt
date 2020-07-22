package com.memo.home.ui.fragment.db

import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.memo.base.entity.remote.BannerResponse
import com.memo.base.manager.router.RouterManager
import com.memo.base.ui.databinding.BaseVmDbFragment
import com.memo.core.tool.ext.finish
import com.memo.core.tool.ext.marginStatusBar
import com.memo.core.tool.ext.onRefreshAndLoadListener
import com.memo.core.tool.ext.paddingStatusBar
import com.memo.home.R
import com.memo.home.databinding.FragmentHomeDbBinding
import com.memo.home.ui.adapter.ArticleAdapter
import com.memo.home.ui.adapter.BannerAdapter
import com.memo.home.ui.fragment.home.HomeViewModel
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.abs

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-07-22 16:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeDbFragment : BaseVmDbFragment<HomeViewModel, FragmentHomeDbBinding>() {

	private val mAdapter by lazy { ArticleAdapter() }

	private lateinit var mBannerView: BannerViewPager<BannerResponse, BannerAdapter.BannerViewHolder>

	/*** 绑定界面 ***/
	override fun bindLayoutRes(): Int = R.layout.fragment_home_db

	override fun bindContentView(): View? = mBinding.mContent

	/*** 初始化数据 ***/
	override fun initData(argument: Bundle?) {}

	/*** 初始化控件 ***/
	override fun initView() {
		// 设置ToolBar距顶
		mToolBar.paddingStatusBar()
		mFlSearch.marginStatusBar()

		// 轮播图
		mBannerView = mBinding.root.findViewById(R.id.mBanner)
		mBannerView.run {
			adapter = BannerAdapter()
			setOnPageClickListener {
				val article = mBannerView.data[it]
				RouterManager.startArticleDetail(article.id, article.title, article.url)
			}
			create()
		}

		// RecycleView
		mBinding.mRvList.run {
			layoutManager = LinearLayoutManager(mContext)
			adapter = mAdapter
		}
	}

	/*** 初始化监听 ***/
	override fun initListener() {
		// 设置AppBar的透明度
		mBinding.mAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
			mBinding.mToolBar.alpha = abs(offset).toFloat() / appBar.totalScrollRange
		})
		// 标题栏点击标题滑动到顶部
		mBinding.mTitleView.setOnTitleClickListener {
			// 但顶部完全显示 滑动到顶部
			if (mBinding.mToolBar.alpha == 1f) {
				val behavior = (mBinding.mAppBar.layoutParams as CoordinatorLayout.LayoutParams).behavior
				if (behavior is AppBarLayout.Behavior) {
					behavior.topAndBottomOffset = 0
				}
				mBinding.mRvList.scrollToPosition(0)
			}
		}
		// 刷新
		mBinding.mRefreshLayout.onRefreshAndLoadListener({
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

	/*** 初始化数据回调 ***/
	override fun initObserver() {
		mViewModel.homeLiveData.observe(this, Observer { data ->
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
			mBinding.mRefreshLayout.finish(data.hasMore)
		})
	}

	/*** 开始数据请求 ***/
	override fun start() {
		mViewModel.getHomeData(true)
	}


}