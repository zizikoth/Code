package com.memo.core.tool.ext

import android.widget.ImageView
import com.memo.core.tool.helper.GlideHelper

/**
 * title:ImageView拓展
 * describe:
 *
 * @author memo
 * @date 2020-08-12 16:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun ImageView.load(url: Any) {
	GlideHelper.loadImage(url, this)
}

fun ImageView.load(url: Any, holderRes: Int) {
	GlideHelper.loadImage(url, holderRes, holderRes, this)
}

fun ImageView.loadRound(url: Any, radius: Int) {
	GlideHelper.loadRoundImage(url, radius, this)
}

fun ImageView.loadRound(url: Any, radius: Int, holderRes: Int) {
	GlideHelper.loadRoundImage(url, radius, holderRes, holderRes, this)
}

fun ImageView.loadCircle(url: Any) {
	GlideHelper.loadCircleImage(url, this)
}

fun ImageView.loadCircle(url: Any, holderRes: Int) {
	GlideHelper.loadCircleImage(url, holderRes, holderRes, this)
}