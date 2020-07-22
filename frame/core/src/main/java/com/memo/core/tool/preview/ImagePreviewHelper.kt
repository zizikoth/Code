package com.memo.core.tool.preview

import android.graphics.Color
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hitomi.tilibrary.style.index.CircleIndexIndicator
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator
import com.hitomi.tilibrary.transfer.TransferConfig
import com.hitomi.tilibrary.transfer.Transferee
import com.memo.core.tool.app.BaseApp
import com.vansz.glideimageloader.GlideImageLoader

/**
 * title: 大图预览帮助类
 * tip:
 *
 * @author zhou
 * @date 2018-09-17 下午1:34
 */
object ImagePreviewHelper {

	fun getConfig(): TransferConfig.Builder {
		return TransferConfig.build()
			.setProgressIndicator(ProgressPieIndicator())
			.setIndexIndicator(CircleIndexIndicator())
			.setImageLoader(GlideImageLoader.with(BaseApp.app.applicationContext))
			.setBackgroundColor(Color.BLACK)
			.setDuration(300)
	}

	/**
	 * 单张图片的放大预览
	 * @param transferee Transferee
	 * @param url String 图片地址
	 * @param view ImageView 点击控件
	 */
	@JvmStatic
	fun start(transferee: Transferee, url: String, view: ImageView) {
		transferee.apply(getConfig().bindImageView(view, url)).show()
	}

	/**
	 * 大图预览
	 * @param transferee Transferee 一个页面一个
	 * @param mData MutableList<String> 数据源
	 * @param recyclerView RecyclerView 列表
	 * @param imageResId Int 资源id
	 */
	@JvmStatic
	fun start(
		transferee: Transferee,
		mData: MutableList<String>,
		recyclerView: RecyclerView,
		imageResId: Int
	) {
		transferee.apply(
			getConfig().setSourceUrlList(mData).bindRecyclerView(recyclerView, imageResId)
		).show()
	}


	/**
	 * 大图预览
	 * @param transferee Transferee 一个页面一个
	 * @param mData MutableList<String> 数据源
	 * @param recyclerView RecyclerView 列表
	 * @param adapter BaseQuickAdapter<*, *> 适配器
	 * @param imageResId Int 资源id
	 */
	@JvmStatic
	fun start(
		transferee: Transferee,
		mData: MutableList<String>,
		recyclerView: RecyclerView,
		adapter: BaseQuickAdapter<*, *>,
		imageResId: Int
	) {
		transferee.apply(
			getConfig().setSourceUrlList(mData)
				.bindRecyclerView(
					recyclerView,
					adapter.headerLayoutCount,
					adapter.footerLayoutCount,
					imageResId
				)
		)
			.show()
	}
}
