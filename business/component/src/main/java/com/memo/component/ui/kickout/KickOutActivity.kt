package com.memo.component.ui.kickout

import android.os.Build
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.ext.color
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_kick_out.*

/**
 * title:提出弹窗的Activity实现方式
 * describe:
 *
 * @author memo
 * @date 2020-06-22 16:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class KickOutActivity : BaseActivity() {

	override fun transparentStatusBar(): Boolean = true

	/*** 绑定布局id ***/
	override fun bindLayoutRes(): Int = R.layout.activity_kick_out

	/*** 进行初始化操作 ***/
	override fun initialize() {
		overridePendingTransition(R.anim.fade_show, 0)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			BarUtils.setNavBarColor(this, color(R.color.color_shadow_dialog))
		}

		mTvNegative.onClick {
			finish()
		}
		mTvPositive.onClick {
			ActivityUtils.finishAllActivities(true)
		}
	}

	override fun onBackPressed() {}

	override fun finish() {
		super.finish()
		overridePendingTransition(0, R.anim.fade_hide)
	}

}