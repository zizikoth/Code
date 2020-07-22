package com.memo.core.tool.dialog.dialog

import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.memo.core.R
import com.memo.core.tool.dialog.listener.OnTipClickListener
import com.memo.core.tool.ext.dimen
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.dialog_tip.view.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-28 11:03
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TipDialog(
	private val title: String = "提示", private val message: String = "内容",
	private val positive: String = "确定", private val negative: String = "取消"
) : BaseDialog() {

	private var mListener: OnTipClickListener? = null

	override fun bindAnimStyle(): Int = R.style.FadeDialogAnim

	override fun bindLayout(): Int = R.layout.dialog_tip

	override fun bindView(root: View) {
		root.mTvTitle.text = title
		root.mTvMessage.text = message
		root.mTvPositive.text = positive
		root.mTvNegative.text = negative
		root.mTvPositive.onClick {
			mListener?.onPositive()
			dismiss()
		}
		root.mTvNegative.onClick {
			mListener?.onNegative()
			dismiss()
		}
	}

	override fun bindParams(params: WindowManager.LayoutParams) {
		params.dimAmount = 0.2f
		params.gravity = Gravity.CENTER
		params.width = dimen(R.dimen.dp280).toInt()
	}

	fun setOnTipClickListener(onPositive: () -> Unit, onNegative: () -> Unit = {}): TipDialog {
		this.mListener = object : OnTipClickListener {
			override fun onPositive() {
				onPositive()
			}

			override fun onNegative() {
				onNegative()
			}
		}
		return this
	}
}