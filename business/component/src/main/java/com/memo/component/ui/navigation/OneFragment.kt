package com.memo.component.ui.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.memo.base.ui.mvvm.BaseFragment
import com.memo.component.R
import com.memo.core.tool.ext.log
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.fragment_one.*

class OneFragment : BaseFragment() {

	override fun bindLayoutRes(): Int = R.layout.fragment_one

	override fun initialize() {
		mBtnTwo.onClick {
			findNavController().navigate(
				R.id.action_oneFragment_to_twoFragment,
				bundleOf("name" to "123", "age" to 12, "sex" to "男")
			)
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == Activity.RESULT_OK) {
			mTvContent.text = data?.getStringExtra("data")
		}
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		log(TAG, "onAttach")
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		log(TAG, "onCreate")
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		log(TAG, "onCreateView")
		return super.onCreateView(inflater, container, savedInstanceState)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		log(TAG, "onActivityCreated")
	}

	override fun onStart() {
		super.onStart()
		log(TAG, "onStart")
	}

	override fun onResume() {
		super.onResume()
		log(TAG, "onResume")
	}

	override fun onPause() {
		super.onPause()
		log(TAG, "onPause")
	}

	override fun onStop() {
		super.onStop()
		log(TAG, "onStop")
	}

	override fun onDestroyView() {
		super.onDestroyView()
		log(TAG, "onDestroyView")
	}

	override fun onDestroy() {
		super.onDestroy()
		log(TAG, "onDestroy")
	}

	override fun onDetach() {
		super.onDetach()
		log(TAG, "onDetach")
	}
}