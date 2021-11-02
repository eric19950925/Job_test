package com.philabnb.job_test.Base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.philabnb.job_test.HomeFragment
import com.philabnb.job_test.R
import org.koin.ext.getFullName

class Navigator {
    lateinit var activity: FragmentActivity
    var lastAddTime: Long = 0
}

fun Navigator.toHome(){
    addPage(HomeFragment.newInstance())
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
    synchronized(activity.supportFragmentManager) {
        val fragmentManager = activity.supportFragmentManager

        if (fragmentManager.backStackEntryCount == 1) {
            for (fragment in fragmentManager.fragments) {
//                if (fragment is EntrancePage
//                ) {
//                    activity.finish()
//                    break
//                }
            }
        } else {
            fragmentManager.popBackStack()
        }
    }
}