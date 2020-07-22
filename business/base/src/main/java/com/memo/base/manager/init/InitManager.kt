package com.memo.base.manager.init

import android.app.Application
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kingja.loadsir.core.LoadSir
import com.memo.base.BuildConfig
import com.memo.base.R
import com.memo.base.app.AppConfig
import com.memo.base.app.AppConstant
import com.memo.base.status.callback.LoadingCallback
import com.memo.base.status.callback.NetErrorCallback
import com.memo.base.status.callback.ServerErrorCallback
import com.memo.core.tool.app.BaseApp
import com.memo.core.tool.ext.dimen
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-28 15:33
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object InitManager {

	private var isInitApp = false
	private var isInitLater = false

	@JvmStatic
	fun initApp(app: Application) {
		if (ProcessUtils.isMainProcess() && !isInitApp) {
			// 滴滴插件
			DoraemonKit.install(app, AppConstant.AppKeys.DoraemonKitId)

			// Utils
			Utils.init(app)
			LogUtils.getConfig().setLogSwitch(AppConfig.isOpenLog).globalTag = "Log"

			// ARouter
			if (BuildConfig.DEBUG) {
				ARouter.openLog()
				ARouter.openDebug()
			}
			ARouter.init(app)

			isInitApp = true
			LogUtils.iTag("init", "App初始化完毕")
		}
	}

	@JvmStatic
	fun initLater() {
		if (!isInitLater) {
			// LoadSir
			LoadSir.beginBuilder()
				.addCallback(LoadingCallback())
				.addCallback(NetErrorCallback())
				.addCallback(ServerErrorCallback())
				.setDefaultCallback(LoadingCallback::class.java)
				.commit()

			// 初始化刷新框架
			SmartRefreshLayout.setDefaultRefreshInitializer { _, refreshLayout ->
				refreshLayout
					.setEnableAutoLoadMore(false)
					.setEnableOverScrollBounce(true)
					.setEnableOverScrollDrag(true)
					.setEnableLoadMoreWhenContentNotFull(false)
			}
			SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
				ClassicsHeader(context)
			}
			SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
				val footer = BallPulseFooter(context)
					.setNormalColor(ContextCompat.getColor(context, R.color.color_666666))
					.setAnimatingColor(ContextCompat.getColor(context, R.color.color_666666))
				footer.minimumHeight = dimen(R.dimen.dp50).toInt()
				footer.minimumWidth = dimen(R.dimen.dp50).toInt()
				footer
			}

			// LiveDataBus
			LiveEventBus.config()
				.lifecycleObserverAlwaysActive(false)
				.setContext(BaseApp.app.applicationContext)
				.enableLogger(AppConfig.isOpenLog)

			isInitLater = true
			LogUtils.iTag("init", "延时初始化完毕")
		}
	}
}