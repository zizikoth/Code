package com.memo.core.tool.ext

import android.content.Context
import android.content.SharedPreferences
import com.memo.core.tool.app.BaseApp

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-09-28 17:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun spEdit(block: SharedPreferences.Editor.() -> Unit) {
	val editor = BaseApp.app.getSharedPreferences("memo", Context.MODE_PRIVATE).edit()
	editor.block()
	editor.apply()
}