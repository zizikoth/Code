/**
 * title:全局依赖
 * describe:
 *
 * @author memo
 * @date 2020-06-12 09:38
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object Versions {
	const val Gradle = "4.0.0"
	const val Kotlin = "1.4.10"
	const val ARouterRegister = "1.0.2"

	const val Junit = "4.13"
	const val TestJunit = "1.1.1"
	const val TestEspresso = "3.2.0"

	const val AppCompat = "1.2.0"
	const val AgentWeb = "4.1.3"
	const val ARouterApi = "1.5.0"
	const val AndroidUtilCode = "1.29.0"
	const val BaseRecyclerViewAdapterHelper = "3.0.4"
	const val BannerViewPager = "3.1.5"
	const val CardView = "1.0.0"
	const val ConstraintLayout = "2.0.0-beta7"
	const val Coroutine = "1.3.3"
	const val Glide = "4.11.0"
	const val LoadSir = "1.3.6"
	const val LuBan = "1.1.8"
	const val LiveDataBus = "1.6.1"
	const val Material = "1.1.0"
	const val MultiDex = "2.0.1"
	const val Matisse = "0.5.3-beta3"
	const val Map3D = "7.5.0"
	const val MapLocation = "5.0.0"
	const val PickerView = "4.1.8"
	const val Qrcode = "1.3.7"
	const val Retrofit = "2.8.1"
	const val RecycleView = "1.0.0"
	const val RWidget = "0.0.4"
	const val RefreshLayout = "2.0.1"
	const val SwipeLayout = "1.2.0"
	const val Transferee = "1.6.1"
	const val Tab = "2.1.2"
	const val UCrop = "2.2.3"

	const val CoreKtx = "1.3.1"
	const val Lifecycle = "2.2.0"

	const val DoKit = "3.1.6"

	const val ARouterCompiler = "1.2.2"
}

object DeptConfig {
	// gradle
	const val Gradle = "com.android.tools.build:gradle:${Versions.Gradle}"
	const val GradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}"
	const val GradleKotlinAndroid =
		"org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Kotlin}"
	const val GradleARouter = "com.alibaba:arouter-register:${Versions.ARouterRegister}"
	const val GradleDoKit = "com.didichuxing.doraemonkit:doraemonkit-plugin:${Versions.DoKit}"

	// Test
	const val Junit = "junit:junit:${Versions.Junit}"
	const val TestJunit = "androidx.test.ext:junit:${Versions.TestJunit}"
	const val TestEspresso = "androidx.test.espresso:espresso-core:${Versions.TestEspresso}"

	// 依赖库
	const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
	const val AgentWeb = "com.just.agentweb:agentweb:${Versions.AgentWeb}"
	const val ARouter = "com.alibaba:arouter-api:${Versions.ARouterApi}"
	const val AndroidUtilCode = "com.blankj:utilcodex:${Versions.AndroidUtilCode}"
	const val AndroidSwipeLayout = "com.daimajia.swipelayout:library:${Versions.SwipeLayout}@aar"
	const val BannerViewPager = "com.github.zhpanvip:BannerViewPager:${Versions.BannerViewPager}"
	const val BaseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BaseRecyclerViewAdapterHelper}"
	const val CardView = "androidx.cardview:cardview:${Versions.CardView}"
	const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
	const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
	const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutine}"
	const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"
	const val GlideOkHttp = "com.github.bumptech.glide:okhttp3-integration:${Versions.Glide}"
	const val LoadSir = "com.kingja.loadsir:loadsir:${Versions.LoadSir}"
	const val LuBan = "top.zibin:Luban:${Versions.LuBan}"
	const val LiveDataBus = "com.jeremyliao:live-event-bus-x:${Versions.LiveDataBus}"
	const val Material = "com.google.android.material:material:${Versions.Material}"
	const val MultiDex = "androidx.multidex:multidex:${Versions.MultiDex}"
	const val Matisse = "com.zhihu.android:matisse:${Versions.Matisse}"
	const val Map3D = "com.amap.api:3dmap:${Versions.Map3D}"
	const val MapLocation = "com.amap.api:location:${Versions.MapLocation}"
	const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin}"
	const val PickerView = "com.contrarywind:Android-PickerView:${Versions.PickerView}"
	const val QrCode = "cn.bingoogolapple:bga-qrcode-zxing:${Versions.Qrcode}"
	const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
	const val RetrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit}"
	const val RetrofitConverter = "com.squareup.retrofit2:converter-scalars:${Versions.Retrofit}"
	const val RecyclerView = "androidx.recyclerview:recyclerview:${Versions.RecycleView}"
	const val RWidgetHelper = "com.ruffian.library:RWidgetHelper-AndroidX:${Versions.RWidget}"
	const val RefreshLayout = "com.scwang.smart:refresh-layout-kernel:${Versions.RefreshLayout}"
	const val RefreshLayoutHeader = "com.scwang.smart:refresh-header-classics:${Versions.RefreshLayout}"
	const val RefreshLayoutFooter = "com.scwang.smart:refresh-footer-ball:${Versions.RefreshLayout}"
	const val Transferee = "com.github.Hitomis.transferee:Transferee:${Versions.Transferee}"
	const val TransfereeGlide = "com.github.Hitomis.transferee:GlideImageLoader:${Versions.Transferee}"
	const val FlycoTabLayout = "com.flyco.tablayout:FlycoTabLayout_Lib:${Versions.Tab}@aar"
	const val UCrop = "com.github.yalantis:ucrop:${Versions.UCrop}"

	// lifecycle
	const val LifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Lifecycle}"
	const val LifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}"
	const val LifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Lifecycle}"
	const val LifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle}"


	// debugApi
	const val DoKitDebug = "com.didichuxing.doraemonkit:doraemonkit:${Versions.DoKit}"
	const val DoKitLeakDebug = "com.didichuxing.doraemonkit:doraemonkit-leakcanary:${Versions.DoKit}"

	// releaseApi
	const val DoKitRelease = "com.didichuxing.doraemonkit:doraemonkit-no-op:${Versions.DoKit}"

	// kapt
	const val ARouterCompiler = "com.alibaba:arouter-compiler:${Versions.ARouterCompiler}"
	const val GlideCompiler = "com.github.bumptech.glide:compiler:${Versions.Glide}"
}