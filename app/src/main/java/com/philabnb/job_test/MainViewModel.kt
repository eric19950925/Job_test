package com.philabnb.job_test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philabnb.job_test.Model.DataModelItem
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private var toDataPage:()->Unit,
    private var toWebViewPage:()->Unit,
    private var toCathaybkPage:()->Unit,
): ViewModel() {
    val dataList = MutableLiveData<List<DataModelItem>>()
    val ca_dataList = MutableLiveData<List<String>>()
    val apodSite = MutableLiveData<String>()
    val actionBarTitle = MutableLiveData<String>()
    val currentPage = MutableLiveData<Int>()


    fun toWebViewPage(){
        toWebViewPage.invoke()
    }

    fun sent_request(){
        val client = OkHttpClient()
        var body = FormBody.Builder()
            .add("username", "施懿宸")
            .build()

        val request = Request.Builder()
            .post(body)
            .url("https://cloud.mds.com.tw/WistronMobile/SysFun/WebService/LoginChk_Test.aspx")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("TAG",e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
    //            Log.d("TAG",response.body?.string()?:"")
                val rsp = response.body?.string()?:""
                if(rsp.equals("Success!!")){
                    toDataPage.invoke()
                }
            }
        })
    }
    fun toCathaybk(){
        toCathaybkPage.invoke()
    }
    fun createCaData(){
        val list = emptyList<String>().toMutableList()
        val c = Calendar.getInstance()
        c.setTime(Date())
        val ca_sdf = SimpleDateFormat("MM/dd (EEEE)", Locale.ENGLISH)
        ca_sdf.timeZone = TimeZone.getDefault()
        val currentDate = ca_sdf.format(c.time)
        list.add(currentDate)
        for(i in 1..19){
            c.add(Calendar.DAY_OF_WEEK, 1)
            val futureDate = ca_sdf.format(c.time)
            list.add(futureDate)
        }
        Log.d("TAG",list.toString())
        ca_dataList.value = list
    }

}