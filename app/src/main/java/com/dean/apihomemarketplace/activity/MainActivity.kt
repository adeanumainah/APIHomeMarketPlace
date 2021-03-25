package com.dean.apihomemarketplace.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.fragment.HomeFragment
import com.dean.apihomemarketplace.fragment.ProfileFragment
import com.dean.apihomemarketplace.fragment.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item  ->
        when (item.itemId){
            R.id.navigation_home -> {
                val homeFragment = HomeFragment()
                addFragment (homeFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_favorite -> {
                val favoriteFragment = FavoriteFragment()
                addFragment (favoriteFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profile -> {
                val profileFragment = ProfileFragment()
                addFragment (profileFragment)
                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fm_main, fragment, fragment::class.java.simpleName)
                .addToBackStack(null).commit()
    }

    val defaultMainView = HomeFragment.defaultFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        nav_main.setOnNavigationItemSelectedListener  (onNavigationItemSelectedListener)
        addFragment(defaultMainView)
    }

}