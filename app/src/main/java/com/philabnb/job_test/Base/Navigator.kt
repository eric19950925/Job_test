package com.philabnb.job_test.Base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.philabnb.job_test.*
import org.koin.ext.getFullName

class Navigator {
    lateinit var activity: FragmentActivity
    var lastAddTime: Long = 0
}

fun Navigator.toHome(){
    addPage(HomeFragment.newInstance())
}
fun Navigator.toDataPage(){
    addPage(DataPageFragment.newInstance())
}

fun Navigator.toWebViewPage(){
    addPage(WebPageFragment.newInstance())
}

fun Navigator.toCathaybkPage(){
    addPage(Cathaybk.newInstance())
}
fun Navigator.addPage(fragment: Fragment){
    //unknow
    if (System.currentTimeMillis() - lastAddTime < 500) {
        return
    }
    lastAddTime = System.currentTimeMillis()
    if (activity.supportFragmentManager.fragments.size > 0) {
        var needToHideFragment: Fragment? = null
        for (topFragment in activity.supportFragmentManager.fragments) {
            if (topFragment.isVisible) {
                needToHideFragment = topFragment
            }
        }

        if (needToHideFragment == null) {
            activity.supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    0,//R.anim.anim_in,
                    0,//R.anim.anim_out,
                    0,//R.anim.pop_in,
                    0,//R.anim.pop_out
                )
                .add(R.id.container, fragment, fragment::class.getFullName())
                .addToBackStack(fragment::class.getFullName())
                .commit()
        } else {
            activity.supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    0,//R.anim.anim_in,
                    0,//R.anim.anim_out,
                    0,//R.anim.pop_in,
                    0,//R.anim.pop_out
                )
                .add(R.id.container, fragment, fragment::class.getFullName())
                .hide(needToHideFragment)
                .addToBackStack(fragment::class.getFullName())
                .commit()
        }
    } else {
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                0,//R.anim.anim_in,
                0,//R.anim.anim_out,
                0,//R.anim.pop_in,
                0,//R.anim.pop_out
            )
            .add(R.id.container, fragment, fragment::class.getFullName())
            .addToBackStack(fragment::class.getFullName())
            .commit()
    }


}
fun Navigator.onBackPress() {
    val fragmentManager = activity.supportFragmentManager
    fragmentManager.popBackStack()
}