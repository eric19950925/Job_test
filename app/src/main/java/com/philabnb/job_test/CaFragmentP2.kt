package com.philabnb.job_test

import com.philabnb.job_test.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ca_p2.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CaFragmentP2 : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.fragment_ca_p2
    private val viewModel by sharedViewModel<MainViewModel>()
    override fun initData() {

    }

    override fun initObserver() {
    }

    override fun initView() {
        btn_to_p1.listenClick {
            viewModel.currentPage.value = 0
            viewModel.actionBarTitle.value = "線上測驗A"
        }
    }
}