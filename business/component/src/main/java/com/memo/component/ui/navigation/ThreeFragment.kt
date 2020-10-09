package com.memo.component.ui.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.memo.base.ui.mvvm.BaseFragment
import com.memo.component.R
import com.memo.core.tool.ext.log
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.fragment_three.*


class ThreeFragment : BaseFragment() {

	override fun bindLayoutRes(): Int = R.layout.fragment_three

	override fun initialize() {
		mBtnOne.onClick { findNavController().navigate(R.id.action_threeFragment_to_oneFragment) }
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