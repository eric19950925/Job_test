package com.philabnb.job_test

import com.philabnb.job_test.Base.BaseFragment

import kotlinx.android.synthetic.main.fragment_web.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class WebPageFragment: BaseFragment(){
    override fun getLayoutRes(): Int = R.layout.fragment_web
    private val viewModel by sharedViewModel<MainViewModel>()
    override fun initData() {
    }

    override fun initObserver() {
    }

    override fun initView() {
        webview.loadUrl(viewModel.apodSite.value.toString())
    }
    companion object {
        fun newInstance() = WebPageFragment()
    }
}