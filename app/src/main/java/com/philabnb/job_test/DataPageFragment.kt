package com.philabnb.job_test

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.philabnb.job_test.Base.BaseFragment
import com.philabnb.job_test.Model.DataModelItem
import kotlinx.android.synthetic.main.fragment_datapage.*
import okhttp3.*
import org.json.JSONArray
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.io.IOException

class DataPageFragment : BaseFragment(){
    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var myAdapter: DataAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_datapage

    override fun initData() {
    }

    override fun initObserver() {
        observe(viewModel.dataList){
            myAdapter = DataAdapter(it.toMutableList()){
                viewModel.apodSite.value = it
                viewModel.toWebViewPage()
            }
            rv_data.adapter = myAdapter
        }
    }

    override fun onFragmentShow() {
    }

    override fun initView() {

        getData {
            activity?.runOnUiThread{
                viewModel.dataList.value = it
            }
        }

        myAdapter = DataAdapter(emptyList<DataModelItem>().toMutableList()){}
        rv_data.layoutManager = LinearLayoutManager(context)
        rv_data.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        rv_data.adapter = myAdapter
    }

    private fun getData(onNext:((MutableList<DataModelItem>)-> Unit) ?= null){
        val dataList: ArrayList<DataModelItem> = ArrayList()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/cmmobile/NasaDataSet/main/apod.json")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("TAG",e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val rsp = response.body?.string()?:""
                val json = JSONArray(rsp)

                for(i in 0..(json.length() - 1)){
                    val item = json.getJSONObject(i)

                    val description = item.get("description").toString()
                    val copyright = item.get("copyright").toString()
                    val title = item.get("title").toString()
                    val url = item.get("url").toString()
                    val apod_site = item.get("apod_site").toString()
                    val date = item.get("date").toString()
                    val media_type = item.get("media_type").toString()
                    val hdurl = item.get("hdurl").toString()

                    val data = DataModelItem(apod_site, copyright, date, description, hdurl, media_type, title, url)
                    Log.d("TAG",date)
                    dataList.add(data)
                }
                onNext?.invoke(dataList)
            }
        })
    }

    companion object {
        fun newInstance() = DataPageFragment()
    }

}
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(this, Observer {
        block(it)
    })
}
