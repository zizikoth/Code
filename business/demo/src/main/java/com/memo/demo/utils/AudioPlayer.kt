package com.memo.demo.utils

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils
import com.memo.core.tool.ext.tryCatch

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-10-26 09:52
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class AudioPlayer(lifecycleOwner: LifecycleOwner) : LifecycleObserver {

	private val TAG = this.javaClass.simpleName

	private val mPlayer: MediaPlayer = MediaPlayer()

	private var isPause = false

	init {
		lifecycleOwner.lifecycle.addObserver(this)
		mPlayer.setOnErrorListener { _, _, _ ->
			mPlayer.reset()
			false
		}
	}

	fun playAudio(path: String, listener: MediaPlayer.OnCompletionListener? = null) {
		tryCatch({
			mPlayer.reset()
			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
			mPlayer.setOnCompletionListener(listener)
			mPlayer.setDataSource(path)
			mPlayer.prepare()
			mPlayer.start()
			isPause = false
			LogUtils.iTag(TAG, "开始播放")
		})
	}

	fun isPlaying(): Boolean = mPlayer.isPlaying

	fun isPause(): Boolean = isPause

	fun pause() {
		if (mPlayer.isPlaying) {
			LogUtils.iTag(TAG, "暂停播放")
			mPlayer.pause()
			isPause = true
		}
	}

	fun resume() {
		if (!mPlayer.isPlaying) {
			LogUtils.iTag(TAG, "恢复播放")
			mPlayer.start()
			isPause = false
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	fun stop() {
		if (mPlayer.isPlaying) {
			mPlayer.stop()
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	fun destroy() {
		mPlayer.release()
	}
}