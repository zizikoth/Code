package com.memo.core.tool.dialog.listener


/**
 * title:弹窗条目点击
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 14:35
 */
interface OnTipClickListener {
	/**
	 * 点击确定
	 */
	fun onPositive()

	/**
	 * 点击取消
	 */
	fun onNegative()
}