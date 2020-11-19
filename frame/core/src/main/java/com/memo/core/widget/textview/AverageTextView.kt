package com.memo.core.widget.textview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.memo.core.R

/**
 * title:平均分布的TextView
 * describe:需要在xml文件中申明控件的宽高
 *
 * @author memo
 * @date 2020-11-19 3:02 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class AverageTextView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	enum class Gravity(val value: Int) {
		Top(1),
		Center(2),
		Bottom(3)
	}

	/*** 显示文字 ***/
	var text = if (isInEditMode) "这里是测试数据" else ""
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 字体大小 ***/
	var textSize = sp2px(13f)
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 字体颜色 ***/
	var textColor = Color.BLACK
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 是否粗体 ***/
	var textBold = false
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 分布格式 ***/
	var textGravity = Gravity.Center
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}


	private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

	init {
		val attr = context.obtainStyledAttributes(attrs, R.styleable.AverageTextView)
		text = attr.getString(R.styleable.AverageTextView_average_text) ?: text
		textSize = attr.getDimensionPixelSize(R.styleable.AverageTextView_average_textSize, textSize.toInt()).toFloat()
		textColor = attr.getColor(R.styleable.AverageTextView_average_textColor, textColor)
		textBold = attr.getBoolean(R.styleable.AverageTextView_average_textBold, textBold)
		textGravity = when (attr.getInt(R.styleable.AverageTextView_average_gravity, Gravity.Center.value)) {
			1 -> Gravity.Top
			2 -> Gravity.Center
			3 -> Gravity.Bottom
			else -> Gravity.Center
		}
		attr.recycle()
	}

	@SuppressLint("DrawAllocation")
	override fun onDraw(canvas: Canvas) {
		if (text.isEmpty()) return
		mPaint.color = textColor
		mPaint.textSize = textSize
		mPaint.isFakeBoldText = textBold
		// 单个文字的集合
		val charArray = text.toCharArray()
		// 单个文字的宽度集合
		val charWidthArray = mutableListOf<Float>()
		// 文字绘制的x
		var x = 0f
		charArray.forEach {
			val charWidth = mPaint.measureText(it.toString())
			charWidthArray.add(charWidth)
		}
		// 文字绘制的y
		val y = when (textGravity) {
			Gravity.Top -> {
				-mPaint.fontMetrics.top
			}
			Gravity.Center -> {
				(measuredHeight - (mPaint.fontMetrics.ascent + mPaint.fontMetrics.descent)) / 2f
			}
			Gravity.Bottom -> {
				measuredHeight - mPaint.fontMetrics.bottom
			}
		}

		// 计算所有文字的宽度
		val totalTextWidth = mPaint.measureText(text)
		// 计算文字之间的间隔
		val textBlankWidth = (measuredWidth - totalTextWidth) / (text.length - 1)
		// 开始绘制文字
		charArray.forEachIndexed { index, char ->
			canvas.drawText(char.toString(), x, y, mPaint)
			x += (charWidthArray[index] + textBlankWidth)
		}
	}

	private fun sp2px(spValue: Float): Float = spValue * Resources.getSystem().displayMetrics.scaledDensity

}