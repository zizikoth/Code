package com.memo.base.status.callback

import com.kingja.loadsir.callback.Callback
import com.memo.base.R

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-26 14:33
 */
class NetErrorCallback : Callback() {

	override fun onCreateView(): Int = R.layout.layout_status_network_error_view
}