package com.memo.core.tool.dialog.dialog

import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.memo.core.R
import com.memo.core.tool.ext.dimen
import kotlinx.android.synthetic.main.dialog_load.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-28 13:22
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LoadingDialog : BaseDialog() {

	override fun bindLayout(): Int = R.layout.dialog_load

	override fun bindView(root: View) {}

	override fun bindParams(params: WindowManager.LayoutParams) {
		params.dimAmount = 0.2f
		params.gravity = Gravity.CENTER
		params.width = dimen(R.dimen.dp120).toInt()
		params.height = dimen(R.dimen.dp120).toInt()
	}

	override fun bindAnimStyle(): Int = R.style.FadeDialogAnim

	fun showLoading(fragmentManager: FragmentManager, tip: String) {
		mRootView?.mTvMessage?.text = tip
		show(fragmentManager)
	}

	fun hideLoading() {
		if (isVisible) {
			dismiss()
		}
	}

}