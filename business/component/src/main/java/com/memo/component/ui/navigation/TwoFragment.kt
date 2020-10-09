package com.memo.component.ui.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.memo.base.ui.mvvm.BaseFragment
import com.memo.component.R
import com.memo.core.tool.ext.log
import com.memo.core.tool.ext.onClick
import kotlinx.android.synthetic.main.fragment_two.*

@SuppressLint("SetTextI18n")
class TwoFragment : BaseFragment() {

	override fun bindLayoutRes(): Int = R.layout.fragment_two

	override fun initialize() {
		arguments?.let {
			val name = it.getString("name")
			val age = it.getInt("age")
			val sex = it.getString("sex")
			mTvContent.text = "name = $name age = $age sex = $sex"
		}
		mBtnThree.onClick { findNavController().navigate(R.id.action_twoFragment_to_threeFragment) }
		mBtnBack.onClick {
			val intent = Intent().putExtra("data", "this is data")
			targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
			findNavController().popBackStack()
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