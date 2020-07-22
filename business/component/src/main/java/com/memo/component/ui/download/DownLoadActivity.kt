package com.memo.component.ui.download

import android.annotation.SuppressLint
import com.blankj.utilcode.util.AppUtils
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.dir.LocalDir
import com.memo.core.tool.ext.onClick
import com.memo.core.tool.helper.PermissionHelper
import kotlinx.android.synthetic.main.activity_down_load.*
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
class DownLoadActivity : BaseActivity() {

	private val downUrl = "https://downpack.baidu.com/appsearch_AndroidPhone_1012271b.apk"


	override fun bindLayoutRes(): Int = R.layout.activity_down_load

	override fun initialize() {
		mBtnService.onClick {
			if (PermissionHelper.grantedInstallUnKnowApp(mContext, 1)) {
				PermissionHelper.grantedStorage(mContext) {
					DownloadManager.get().start(downUrl, LocalDir.CACHE_DIR_FILE, "temp.apk",
						object : DownloadManager.OnProgressListener {
							override fun onProgress(progress: Int) {
								mContext.runOnUiThread { mTvProgress.text = progress.toString() }
							}

							override fun onFinish(file: File) {
								AppUtils.installApp(file)
							}
						})
				}
			}
		}
	}

}
