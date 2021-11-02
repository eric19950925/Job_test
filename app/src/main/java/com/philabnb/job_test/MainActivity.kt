package com.philabnb.job_test

import android.content.DialogInterface
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.philabnb.job_test.Base.BaseFragment
import com.philabnb.job_test.Base.Navigator
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val navigator by inject<Navigator>()
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        navigator.activity=this

    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.filter { it.isVisible && it is BaseFragment }.forEach {
            if (it is BaseFragment) {
                it.onBackPress()
            }
        }
    }
    fun confirmExit() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("確定要離開?")
        builder.setPositiveButton("確定", DialogInterface.OnClickListener { dialog, which ->
            finish()
            dialog.dismiss()
        }).setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        builder.show()
    }
}