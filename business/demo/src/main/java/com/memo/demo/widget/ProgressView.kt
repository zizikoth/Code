package com.memo.demo.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import kotlin.math.min

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-10-28 15:50
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProgressView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	/*** 背景颜色 ***/
	@ColorInt
	var bgColor: Int = Color.parseColor("#33FFFFFF")
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 百分比进度颜色 ***/
	@ColorInt
	var progressColor: Int = Color.WHITE
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 文字颜色 ***/
	@ColorInt
	var textColor: Int = Color.WHITE
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	/*** 进度百分比 ***/
	var progress: Int = 0
		set(value) {
			if (field != value) {
				getAngleAnim(field.toFloat(), value.toFloat()).start()
				getTextAnim(field, value).start()
				field = value
			}
		}
	private var progressAngle = 0f
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}
	private var progressText = 0
		set(value) {
			if (field != value) {
				field = value
				invalidate()
			}
		}

	private fun getAngleAnim(startPercent: Float, endPercent: Float): ObjectAnimator {
		val startAngle = startPercent / 100 * 360f
		val endAngle = endPercent / 100 * 360f
		return ObjectAnimator.ofFloat(this, "progressAngle", startAngle, endAngle).apply {
			duration = 500
		}
	}

	private fun getTextAnim(startPercent: Int, endPercent: Int): ObjectAnimator {
		return ObjectAnimator.ofInt(this, "progressText", startPercent, endPercent).apply {
			duration = 500
		}
	}

	/*** View的宽高 ***/
	private var size = 0f

	/*** 背景画笔 ***/
	private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

	/*** 文字画笔 ***/
	private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

	/*** 文字测量 ***/
	private val mTextBounds = Rect()

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		size = min(measuredWidth, measuredHeight).toFloat()
		// 计算绘制圆环宽度
		mPaint.strokeWidth = size / 4
		mPaint.style = Paint.Style.STROKE
		// 计算TextSize
		val textSize = (size / 2 - 5 * 2) / 4
		mTextPaint.textSize = textSize
		mTextPaint.textAlign = Paint.Align.CENTER
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		// 绘制背景
		mPaint.color = bgColor
		canvas.drawCircle(size / 2, size / 2, (size - size / 4) / 2, mPaint)
		// 绘制进度
		mPaint.color = progressColor
		canvas.drawArc(
			size / 8,
			size / 8,
			size - size / 8,
			size - size / 8,
			-90f,
			-progressAngle,
			false,
			mPaint
		)
		// 绘制文字
		mTextPaint.color = textColor
		val text = "$progressText%"
		mTextPaint.getTextBounds(text, 0, text.length, mTextBounds)
		canvas.drawText(text, size / 2, size / 2 - (mTextBounds.top + mTextBounds.bottom) / 2, mTextPaint)
	}

}