package com.memo.component.launcher

import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.ui.mvvm.BaseFragment
import com.memo.component.R
import com.memo.component.adapter.StringAdapter
import com.memo.component.ui.anim.AnimActivity
import com.memo.component.ui.bottom.BottomActivity
import com.memo.component.ui.bottomsheet.ActionBottomSheetDialog
import com.memo.component.ui.bottomsheet.BottomSheetActivity
import com.memo.component.ui.bus.BusSubscribeActivity
import com.memo.component.ui.clip.ClipPaddingActivity
import com.memo.component.ui.file.FileActivity
import com.memo.component.ui.fold.FoldActivity
import com.memo.component.ui.handler.HandlerActivity
import com.memo.component.ui.kickout.KickOutActivity
import com.memo.component.ui.motionlayout.MotionLayout1Activity
import com.memo.component.ui.motionlayout.MotionLayout2Activity
import com.memo.component.ui.motionlayout.MotionLayout3Activity
import com.memo.component.ui.navigation.NavigationActivity
import com.memo.component.ui.share.ShareElementListActivity
import com.memo.component.ui.update.UpdateActivity
import com.memo.component.ui.vp2.ViewPager2Activity
import com.memo.core.tool.dialog.dialog.BottomGridDialog
import com.memo.core.tool.dialog.dialog.BottomListDialog
import com.memo.core.tool.dialog.dialog.LoadingDialog
import com.memo.core.tool.dialog.dialog.TipDialog
import com.memo.core.tool.ext.startActivity
import com.memo.core.tool.ext.toast
import kotlinx.android.synthetic.main.fragment_component.*


class ComponentFragment : BaseFragment() {

	private val mAdapter by lazy { StringAdapter() }

	private var dialogIndex = 0
	private val mLoadingDialog by lazy { LoadingDialog() }
	private val mTipDialog by lazy { TipDialog().setOnTipClickListener({ toast("点击确定") }, { toast("点击取消") }) }
	private val mBottomListDialog by lazy {
		BottomListDialog(arrayListOf("Item 1", "Item 2", "Item 3"))
			.setOnItemClickListener { _, item -> toast(item) }
	}
	private val mBottomGridDialog by lazy {
		BottomGridDialog(
			arrayListOf(
				BottomGridDialog.GridItem(R.drawable.frame, "Item 1"),
				BottomGridDialog.GridItem(R.drawable.frame, "Item 2"),
				BottomGridDialog.GridItem(R.drawable.frame, "Item 3")
			)
		)
			.setOnItemClickListener { position, item ->
				toast("position = $position  name = ${item.name}")
			}
	}
	private val mActionDialog by lazy {
		ActionBottomSheetDialog().setData(
			arrayListOf(
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
			)
		)
			.setOnItemClickListener { _, position, data ->
				toast("position = $position data = $data")
			}
	}


	private val data by lazy {
		arrayListOf(
			"MotionLayout1",
			"MotionLayout2",
			"MotionLayout3",
			"Anim动画",
			"BottomBar",
			"BottomSheetDialog",
			"LiveEventBus",
			"KickOut踢出弹窗",
			"Update",
			"共享元素",
			"Dialog",
			"File",
			"Fold",
			"Handler",
			"Navigation",
			"ViewPager2",
			"ClipToPadding"
		)
	}

	override fun bindLayoutRes(): Int = R.layout.fragment_component

	override fun initialize() {
		initView()
		initListener()
	}

	private fun initView() {
		mRvList.run {
			layoutManager = LinearLayoutManager(mContext)
			mAdapter.data = data
			adapter = mAdapter
		}
	}

	private fun initListener() {
		mAdapter.setOnItemClickListener { _, view, position ->
			when (mAdapter.data[position]) {
				"MotionLayout1" -> startActivity<MotionLayout1Activity>()
				"MotionLayout2" -> startActivity<MotionLayout2Activity>()
				"MotionLayout3" -> startActivity<MotionLayout3Activity>()
				"Anim动画" -> startActivity<AnimActivity>()
				"BottomBar" -> startActivity<BottomActivity>()
				"BottomSheetDialog" -> startActivity<BottomSheetActivity>()
				"LiveEventBus" -> startActivity<BusSubscribeActivity>()
				"KickOut踢出弹窗" -> startActivity<KickOutActivity>()
				"Update" -> startActivity<UpdateActivity>()
				"共享元素" -> startActivity<ShareElementListActivity>()
				"Dialog" -> {
					when (dialogIndex++ % 5) {
						0 -> {
							mLoadingDialog.showLoading(childFragmentManager, "加载中")
							view.postDelayed({ mLoadingDialog.hideLoading() }, 1500)
						}
						1 -> mTipDialog.show(childFragmentManager)
						2 -> mBottomListDialog.show(childFragmentManager)
						3 -> mBottomGridDialog.show(childFragmentManager)
						4 -> mActionDialog.show(childFragmentManager)
					}
				}
				"File" -> startActivity<FileActivity>()
				"Fold" -> startActivity<FoldActivity>()
				"Handler" -> startActivity<HandlerActivity>()
				"Navigation" -> startActivity<NavigationActivity>()
				"ViewPager2" -> startActivity<ViewPager2Activity>()
				"ClipToPadding" -> startActivity<ClipPaddingActivity>()
			}
		}
	}

}
