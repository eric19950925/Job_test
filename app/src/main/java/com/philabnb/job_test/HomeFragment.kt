package com.philabnb.job_test

import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.view.clicks
import com.philabnb.job_test.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment(){
    private val viewModel by sharedViewModel<MainViewModel>()
    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun initData() {
    }

    override fun initObserver() {
    }

    override fun onFragmentShow() {
    }

    override fun initView() {
        btn_request.listenClick {
            viewModel.sent_request()
        }

        btn_cathaybk.listenClick {
            viewModel.toCathaybk()
        }

    }
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onBackPress(){
        (activity as MainActivity).confirmExit()
    }
}
fun View?.listenClick(throttleTimeMilliSec: Long = 500, onClick: (View) -> Unit) {
    this?:return
    clicks()
        .throttleFirst(throttleTimeMilliSec, TimeUnit.MILLISECONDS)
        .subscribe({
            performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            onClick(this)
        }, {
            it.message
        })
}
fun Fragment.toast(message: Any?) {
    activity?.let {
        Toast.makeText(it, message.toString(), Toast.LENGTH_SHORT).show()
    }
}