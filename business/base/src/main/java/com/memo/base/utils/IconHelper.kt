package com.memo.base.utils

import com.memo.base.R
import kotlin.math.abs

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 10:33
 * @email zhou_android@163.com
 *
 *
 * Talk is cheap, Show me the code.
 */
object IconHelper {

	private val icons = arrayOf(
		R.mipmap.ic_ahri,
		R.mipmap.ic_ezreal,
		R.mipmap.ic_garen,
		R.mipmap.ic_jinx,
		R.mipmap.ic_katarina,
		R.mipmap.ic_lisin,
		R.mipmap.ic_luxanna,
		R.mipmap.ic_sona,
		R.mipmap.ic_teemo,
		R.mipmap.ic_tristana
	)

	@JvmStatic
	fun randomIcon(id: Int): Int {
		return icons[abs(id % 10)]
	}
}

