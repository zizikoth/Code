package com.memo.component.ui.bus

import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.ext.onClick
import com.memo.core.tool.ext.startActivity
import kotlinx.android.synthetic.main.activity_bus_subscribe.*

/**
 * title:LiveEventBus注册接收
 * describe:
 *
 * @author memo
 * @date 2020-06-22 15:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BusSubscribeActivity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_bus_subscribe

	override fun initialize() {
		LiveEventBus.get("LiveEventBusTest", String::class.java)
			.observe(mLifecycleOwner, Observer {
				mTvReceive.text = it
			})

		mBtnIntent.onClick {
			startActivity<BusPostActivity>()
		}

		mBtnPostSticky.onClick {
			LiveEventBus.get("LiveEventBusTestSticky", String::class.java)
				.post("这是粘性数据")
			startActivity<BusPostActivity>()
		}
	}

}