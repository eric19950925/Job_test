package com.philabnb.job_test

import android.app.Activity
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.philabnb.job_test.Base.BaseFragment
import com.philabnb.job_test.Base.Navigator
import com.philabnb.job_test.Base.toHome
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
        navigator.toHome()
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

fun Activity.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()