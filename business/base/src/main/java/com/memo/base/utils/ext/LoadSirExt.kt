package com.memo.base.utils.ext

import android.view.View
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * title:LoadSir的拓展函数
 * describe:
 *
 * @author memo
 * @date 2020-04-30 09:32
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun loadSir(contentView: View?, onRetry: () -> Unit): LoadService<*>? =
	if (contentView == null) null else LoadSir.getDefault().register(contentView) { onRetry.invoke() }
