package com.memo.core.tool.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.memo.core.R
import com.memo.core.tool.app.BaseApp
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.layout_toast.view.*

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */
// ------------------------------- Log相关 -------------------------------//
fun log(tag: String, vararg any: Any) = LogUtils.iTag(tag, any)

// ------------------------------- Toast相关 -------------------------------//
fun toast(message: Any?) {
	message?.let {
		if (it.toString().isNotEmpty()) {
			ToastUtils.setGravity(-1, -1, -1)
			ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
		}
	}
}

fun toastCenter(message: Any?) {
	message?.let {
		if (it.toString().isNotEmpty()) {
			ToastUtils.setGravity(Gravity.CENTER, 0, 0)
			ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
		}
	}
}

fun toastCancel() {
	ToastUtils.cancel()
}

fun toast(value: () -> Any?) = toast(value())


// ------------------------------- SmartRefreshLayout相关 -------------------------------//

/**
 * 关闭刷新
 * @param hasMore true -> 加载更多 false -> 不上拉加载
 */
fun SmartRefreshLayout.finish(hasMore: Boolean = true) {
	this.setEnableLoadMore(hasMore)
	when (state) {
		RefreshState.Refreshing -> finishRefresh(400)
		RefreshState.Loading -> finishLoadMore(400)
		else -> {
		}
	}
}

fun SmartRefreshLayout.onRefreshAndLoadListener(onRefresh: () -> Unit, onLoadMore: () -> Unit) {
	this.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
		override fun onLoadMore(refreshLayout: RefreshLayout) {
			onLoadMore.invoke()
		}

		override fun onRefresh(refreshLayout: RefreshLayout) {
			onRefresh.invoke()
		}
	})
}

//---------------------------------------- Int ----------------------------------------

/**
 * 判断Int数据是否小于0 小于0则为0
 */
fun Int.checkLessZero(): Int = if (this < 0) 0 else this

/**
 * 判断数据是否大于99 显示99+
 */
fun Int.checkMore99(): String = if (this > 99) "99+" else this.toString()

//---------------------------------------- String ----------------------------------------

/**
 * 复制到粘贴板
 * @param content 内容
 */
fun copyToClipboard(content: String) {
	val plainText = ClipData.newPlainText("Copy", content)
	val clipboardManager =
		ContextCompat.getSystemService(BaseApp.app.applicationContext, ClipboardManager::class.java)
	clipboardManager?.setPrimaryClip(plainText)
}

/**
 * 从粘贴板上获取复制数据
 */
fun getFromClipboard(): String {
	val clipData = ContextCompat.getSystemService(
		BaseApp.app.applicationContext,
		ClipboardManager::class.java
	)?.primaryClip
	return if (clipData != null && clipData.itemCount > 0) {
		clipData.getItemAt(0).text.toString()
	} else {
		""
	}
}


//---------------------------------------- try-catch ----------------------------------------
fun tryCatch(tryToDo: () -> Unit, catchToDo: (Exception) -> Unit = {}) {
	try {
		tryToDo()
	} catch (e: Exception) {
		e.printStackTrace()
		catchToDo(e)
	}
}

// ------------------------------- 检查空内容 -------------------------------//
fun checkNoNull(vararg lists: Any?): Boolean {
	lists.forEach {
		if (it == null) return false
	}
	return true
}