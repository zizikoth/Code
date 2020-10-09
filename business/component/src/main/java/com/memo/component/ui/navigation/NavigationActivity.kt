package com.memo.component.ui.navigation

import androidx.navigation.findNavController
import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R

/**
 * title:Navigation使用的Fragment之间使用replace进行切换
 * describe:
 *
 * @author memo
 * @date 2020-09-23 13:34
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class NavigationActivity : BaseActivity() {

	override fun bindLayoutRes(): Int = R.layout.activity_navigation

	override fun onSupportNavigateUp(): Boolean = findNavController(R.id.navigation_host_fragment).navigateUp()

	override fun initialize() {
	}
}