package com.philabnb.job_test

import com.philabnb.job_test.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_cathaybk.*
import kotlinx.android.synthetic.main.fragment_cathaybk.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class Cathaybk : BaseFragment(){
    override fun getLayoutRes(): Int = R.layout.fragment_cathaybk
    private val viewModel by sharedViewModel<MainViewModel>()
    override fun initData() {
    }

    override fun initObserver() {
        observe(viewModel.currentPage){
            viewPager.setCurrentItem(it,true)
        }
        observe(viewModel.actionBarTitle){
            cl_title.tv_actionBar_title.text = it
        }
    }

    override fun initView() {
        viewPager.adapter = CaPageAdapter(childFragmentManager)
        viewPager.setSwipePagingEnabled(false)
        tabLayout.setupWithViewPager(viewPager)
        viewModel.actionBarTitle.value = "線上測驗A"

    }
    companion object {
        fun newInstance() = Cathaybk()
    }

}