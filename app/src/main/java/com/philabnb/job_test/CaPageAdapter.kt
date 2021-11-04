package com.philabnb.job_test

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CaPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return CaFragmentP1()
            }
            1 -> {
                return CaFragmentP2()
            }
            else -> {
                return CaFragmentP1()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "分頁1"
            }
            1 -> {
                return "分頁2"
            }
        }
        return super.getPageTitle(position)
    }

}
