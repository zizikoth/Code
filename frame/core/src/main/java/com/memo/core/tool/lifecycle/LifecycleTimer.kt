package com.memo.core.tool.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

/**
 * title:自动处理生命周期定时器
 * describe:
 *
 * @author memo
 * @date 2020-04-27 18:15
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LifecycleTimer(
	private val lifecycleOwner: LifecycleOwner,
	private val time: Long,
	private val duration: Long,
	private val onTimeCountDownThread: (Long) -> Unit
) : Timer(),
	LifecycleObserver {

	/*** 当前进行时间 ***/
	private var currentTime = 0L

	/*** 倒计时任务 ***/
	private var timeTask: TimerTask? = null

	/*** 判断当前任务是否执行 ***/
	private var isStart: Boolean = false

	init {
		this.lifecycleOwner.lifecycle.addObserver(this)
	}


	/**
	 * 开启定时任务
	 */
	fun start() {
		if (!isStart) {
			currentTime = 0L
			timeTask = object : TimerTask() {
				override fun run() {
					currentTime += duration
					onTimeCountDownThread(time - currentTime)
					if (currentTime == time) {
						// 这里只取消task不取消timer
						this.cancel()
						isStart = false
					}
				}
			}
			this.schedule(timeTask, 0, duration)
			isStart = true
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	private fun onDestroy() {
		this.cancel()
		this.lifecycleOwner.lifecycle.removeObserver(this)
	}

}