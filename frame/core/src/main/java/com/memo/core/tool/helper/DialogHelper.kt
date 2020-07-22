package com.memo.core.tool.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.TimeUtils
import com.contrarywind.view.WheelView
import com.memo.core.R
import com.memo.core.tool.dialog.entity.Area
import com.memo.core.tool.dialog.entity.Province
import com.memo.core.tool.ext.color
import com.memo.core.tool.ext.doInBackground
import java.text.SimpleDateFormat

/**
 * title:弹窗提示
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 18:23
 */
object DialogHelper {

	interface Callback {
		fun onPositive()
		fun onNegative()
	}


	/**
	 * 选择时间
	 * @param showArray 年 月 日 时 分 秒
	 * @param format 配合showArray
	 */
	@SuppressLint("SimpleDateFormat")
	@JvmStatic
	fun selectTime(
		mContext: Context,
		showArray: BooleanArray = booleanArrayOf(true, true, true, true, true, false),
		format: String = "yyyy-MM-dd HH:mm",
		method: (time: String) -> Unit
	): TimePickerView {
		val mTimePickerView = TimePickerBuilder(mContext, OnTimeSelectListener { date, _ ->
			method(TimeUtils.date2String(date, SimpleDateFormat(format)))
		})
			.isCyclic(true)
			.setLabel("年", "月", "日", "时", "分", "秒")
			.isCenterLabel(true)
			.setSubmitColor(color(R.color.color_666666))
			.setCancelColor(color(R.color.color_666666))
			.setDividerType(WheelView.DividerType.WRAP)
			.setType(showArray)
			.setOutSideCancelable(true)
			.setContentTextSize(18)
			.isDialog(true)
			.build()
		val mDialog = mTimePickerView.dialog
		if (mDialog != null) {
			val params = FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				Gravity.BOTTOM
			)

			params.leftMargin = 0
			params.rightMargin = 0
			mTimePickerView.dialogContainerLayout.layoutParams = params

			val dialogWindow = mDialog.window
			if (dialogWindow != null) {
				dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim) // 修改动画样式
				dialogWindow.setGravity(Gravity.BOTTOM) // 改成Bottom,底部显示
				dialogWindow.setDimAmount(0.5f)
			}
		}
		return mTimePickerView
	}

	/**
	 * 解析json数据
	 */
	@JvmStatic
	fun parseArea(lifecycleOwner: LifecycleOwner, onSuccess: (area: Area) -> Unit) =
		lifecycleOwner.doInBackground({
			val json = ResourceUtils.readAssets2String("province.json")
			val provinces = GsonHelper.parse2List<Province>(json)
			val cities: ArrayList<ArrayList<String>> = arrayListOf()
			val areas: ArrayList<ArrayList<ArrayList<String>>> = arrayListOf()
			for (province in provinces) {
				val cityList: ArrayList<String> = arrayListOf()
				val areaList: ArrayList<ArrayList<String>> = arrayListOf()
				for (city in province.city) {
					val cityName = city.name
					cityList.add(cityName)
					val cityAreaList = arrayListOf<String>()
					cityAreaList.addAll(city.area)
					areaList.add(cityAreaList)
				}
				cities.add(cityList)
				areas.add(areaList)
			}
			LogUtils.iTag("area", "城市数据加载完成")
			Area(provinces, cities, areas)
		}, onSuccess, {
			LogUtils.eTag("area", it)
		})

	/**
	 * 选择城市
	 */
	@JvmStatic
	fun selectCity(
		mContext: Context,
		area: Area,
		callback: (area: String) -> Unit
	): OptionsPickerView<Any> {
		val mAreaPickerView: OptionsPickerView<Any> = OptionsPickerBuilder(mContext,
			OnOptionsSelectListener { options1, options2, options3, _ ->
				// 返回的分别是三个级别的选中位置
				val opt1tx = if (area.provinces.isNotEmpty())
					area.provinces[options1].pickerViewText
				else
					""

				val opt2tx = if (area.cities.isNotEmpty() && area.cities[options1].isNotEmpty())
					area.cities[options1][options2]
				else
					""

				val opt3tx = if (area.cities.isNotEmpty() &&
					area.areas[options1].isNotEmpty() &&
					area.areas[options1][options2].isNotEmpty()
				)
					area.areas[options1][options2][options3]
				else
					""

				callback("$opt1tx - $opt2tx - $opt3tx")
			})
			.setTitleText("城市选择")
			.setCyclic(true, true, true)
			.isCenterLabel(true)
			.setOutSideCancelable(true)
			.setSubmitColor(color(R.color.color_666666))
			.setCancelColor(color(R.color.color_666666))
			.isDialog(true)
			.setTextColorCenter(Color.BLACK) // 设置选中项文字颜色
			.setContentTextSize(18)
			.build()

		val mDialog = mAreaPickerView.dialog
		if (mDialog != null) {
			val params = FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				Gravity.BOTTOM
			)

			params.leftMargin = 0
			params.rightMargin = 0
			mAreaPickerView.dialogContainerLayout.layoutParams = params

			val dialogWindow = mDialog.window
			if (dialogWindow != null) {
				dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim) // 修改动画样式
				dialogWindow.setGravity(Gravity.BOTTOM) // 改成Bottom,底部显示
				dialogWindow.setDimAmount(0.5f)
			}
		}

		mAreaPickerView.setPicker(area.provinces, area.cities, area.areas) // 三级选择器
		return mAreaPickerView
	}
}