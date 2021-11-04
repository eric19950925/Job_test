package com.philabnb.job_test

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.philabnb.job_test.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ca_p1.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CaFragmentP1 : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.fragment_ca_p1
    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var caDataAdapter: CaDataAdapter
    override fun initData() {
        //產生今日及往後20天之簡易資料
        viewModel.createCaData()
    }

    override fun initObserver() {
        observe(viewModel.ca_dataList){
            caDataAdapter = CaDataAdapter(it.toMutableList())
            rv_ca_data.adapter = caDataAdapter
        }
    }

    override fun initView() {
        //實作rv及adapter
        caDataAdapter = CaDataAdapter(emptyList<String>().toMutableList())
        rv_ca_data.layoutManager = LinearLayoutManager(context)
        rv_ca_data.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        rv_ca_data.adapter = caDataAdapter
        btn_to_p2.listenClick {
            viewModel.currentPage.value = 1
            viewModel.actionBarTitle.value = "線上測驗B"
        }

    }

    override fun onBackPress() {
        super.onBackPress()
        Log.d("TAG","p1")
    }
}