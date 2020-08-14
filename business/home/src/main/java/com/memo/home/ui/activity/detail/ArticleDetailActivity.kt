package com.memo.home.ui.activity.detail

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.home.R
import com.memo.web.utils.WebHelper
import kotlinx.android.synthetic.main.activity_article_detail.*

@Route(path = RouterPath.ArticleDetail)
class ArticleDetailActivity : BaseActivity() {

	private var id: Int = 0
	private var title: String = ""
	private var url: String = ""
	private val webHelper by lazy { WebHelper() }

	override fun bindLayoutRes(): Int = R.layout.activity_article_detail

	override fun initialize() {
		initData()
		initView()
	}

	private fun initData() {
		id = intent.getIntExtra("id", id)
		title = intent.getStringExtra("title") ?: title
		url = intent.getStringExtra("url") ?: url
		LogUtils.iTag("web", id, title, url)
	}

	private fun initView() {
		webHelper.init(mContext, mFlWeb, R.layout.layout_status_server_error_view, url)
		mTitleView.setTitle(title)
	}
}
