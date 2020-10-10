package com.memo.component.ui.update

import android.annotation.SuppressLint
import com.blankj.utilcode.util.AppUtils
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.dir.LocalDir
import com.memo.core.tool.ext.keep2Decimal
import com.memo.core.tool.ext.onClick
import com.memo.core.tool.helper.NotificationHelper
import com.memo.core.tool.helper.PermissionHelper
import kotlinx.android.synthetic.main.activity_update.*
import java.io.File

/**
 * title:文件下载
 * describe:
 *
 * @author memo
 * @date 2020-06-22 16:37
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@SuppressLint("SetTextI18n")
class UpdateActivity : BaseActivity() {

	private val downUrl = "https://ucan.25pp.com/Wandoujia_web_seo_baidu_homepage.apk"
	private val notifyId = 1
	private val channelId = "Update"
	private val channelName = "应用更新"
	private val title = "应用更新"
	private val smallIcon = R.mipmap.ic_launcher_round


	override fun bindLayoutRes(): Int = R.layout.activity_update

	override fun initialize() {
		mBtnService.onClick {
			UpdateManager.get().update(downUrl, LocalDir.CACHE_DIR_FILE, "temp.apk", object : OnProgressListener {
				override fun onProgress(progress: Double) {
					if (mContext.isFinishing.not()) {
						NotificationHelper.sendProgressNotification(notifyId, channelId, channelName, smallIcon, title, progress.toInt())
						mContext.runOnUiThread { mTvProgress.text = progress.keep2Decimal() }
					}
				}

				override fun onFinish(file: File) {
					if (mContext.isFinishing.not() && PermissionHelper.grantedInstallUnKnowApp(mContext, 1)) {
						AppUtils.installApp(file)
					}
				}
			})
		}
	}

}
