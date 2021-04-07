package com.dean.apihomemarketplace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = "Favorite"
        val adapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(FavoriteRumahFragment(), "Home")
        }
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
