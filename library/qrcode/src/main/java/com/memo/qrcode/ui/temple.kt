package com.memo.qrcode.ui

/**
 * title: 页面示例
 * describe:
 *
 * @author memo
 * @date 2020-06-22 13:44
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
//class QRCodeScanActivity : BaseActivity() {
//
//	private val REQUEST_CODE_QRCODE_ALBLUM = 1
//
//	override fun bindLayoutRes() : Int = R.layout.activity_qr_code_scan
//
//	companion object {
//		fun start(activity : Activity, requestCode : Int) {
//			activity.startActivityForResult<QRCodeScanActivity>(requestCode = requestCode)
//		}
//	}
//
//	override fun initialize() {
//		initTitle()
//		initQRCode()
//	}
//
//	private fun initTitle() {
//		mTitleView.setOnRightClickListener {
//			MediaHelper.choosePhoto(mContext, 1, REQUEST_CODE_QRCODE_ALBLUM, showCapture = false, chooseGif = false)
//		}
//	}
//
//	private fun initQRCode() {
//		mZXingView.setDelegate(object : QRCodeView.Delegate {
//			// 扫描成功
//			override fun onScanQRCodeSuccess(result : String?) {
//				finishActivityWithResult("result" to result)
//			}
//
//			// 摄像头亮度变化
//			override fun onCameraAmbientBrightnessChanged(isDark : Boolean) {
//			}
//
//			// 打开相机出错
//			override fun onScanQRCodeOpenCameraError() {
//			}
//		})
//	}
//
//	override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
//		super.onActivityResult(requestCode, resultCode, data)
//		if (requestCode == REQUEST_CODE_QRCODE_ALBLUM && Activity.RESULT_OK == resultCode && data != null) {
//			val results = Matisse.obtainPathResult(data)
//			if (results.isNotEmpty()) {
//				// 从相册图片中获取二维码
//				QRCodeHelper.decodeQRCode(mLifecycleOwner, results[0], {
//					finishActivityWithResult("result" to it)
//				})
//			}
//		}
//	}
//
//	override fun onStart() {
//		super.onStart()
//		// 开启摄像头
//		mZXingView.startCamera()
//		// 开启扫描
//		mZXingView.startSpotAndShowRect()
//	}
//
//	override fun onDestroy() {
//		super.onDestroy()
//		mZXingView.onDestroy()
//	}
//}
//


//
//<?xml version="1.0" encoding="utf-8"?>
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//xmlns:tools="http://schemas.android.com/tools"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:orientation="vertical"
//tools:context=".qrcode.QRCodeScanActivity">
//
//<com.memo.base.widget.titleview.TitleView
//android:id="@+id/mTitleView"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//app:title_right_text="相册"
//app:title_title_text="二维码扫描" />
//
//<cn.bingoogolapple.qrcode.zxing.ZXingView
//android:id="@+id/mZXingView"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//app:qrcv_animTime="800"
//app:qrcv_borderColor="@android:color/white"
//app:qrcv_borderSize="@dimen/dp0_5"
//app:qrcv_cornerColor="@android:color/white"
//app:qrcv_cornerLength="@dimen/dp20"
//app:qrcv_cornerSize="@dimen/dp3"
//app:qrcv_maskColor="#33000000"
//app:qrcv_rectWidth="@dimen/dp200"
//app:qrcv_scanLineColor="@android:color/white"
//app:qrcv_scanLineSize="@dimen/dp0_5"
//app:qrcv_verticalBias="0.5" />
//
//</LinearLayout>