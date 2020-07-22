package com.memo.component.ui.bus

import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.activity_bus_post.*

/**
 * title:LiveEventBus发送
 * describe:
 *
 * @author memo
 * @date 2020-06-22 15:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BusPostActivity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_bus_post

	override fun initialize() {
		LiveEventBus.get("LiveEventBusTestSticky", String::class.java)
			.observeSticky(mLifecycleOwner, Observer {
				mTvSticky.text = it
			})
		mBtn.onClick {
			LiveEventBus.get("LiveEventBusTest")
				.post("测试数据发送")
		}
	}
}