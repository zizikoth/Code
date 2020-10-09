package com.memo.core.tool.lifecycle

import android.os.CountDownTimer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

abstract class LifecycleCountDownTimer(lifecycleOwner: LifecycleOwner, totalMillis: Long, intervalMillis: Long) :
	CountDownTimer(totalMillis, intervalMillis), LifecycleObserver {
	init {
		lifecycleOwner.lifecycle.addObserver(this)
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	private fun onDestroy() {
		this.cancel()
	}
}