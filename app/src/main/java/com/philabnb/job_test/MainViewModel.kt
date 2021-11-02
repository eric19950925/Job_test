package com.philabnb.job_test

import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.*
import java.io.IOException

class MainViewModel(
//    private var toPage1:()->Unit,
//    private var toPage2:()->Unit,
//    private var toPage3:()->Unit,
//    private var toPage4:()->Unit,
//    private var toPage5:()->Unit
): ViewModel() {
//    fun toP1(){
//        toPage1()
//    }
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
            Log.d("TAG",response.body?.string()?:"")
        }
    })
    }

}