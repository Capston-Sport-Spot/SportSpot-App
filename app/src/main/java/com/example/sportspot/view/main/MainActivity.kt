package com.example.sportspot.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sportspot.R
import com.example.sportspot.api.ApiService
import com.example.sportspot.databinding.ActivityMainBinding
import com.example.sportspot.preferences.UserPreferences
import com.example.sportspot.view.booking.BookingFragment
import com.example.sportspot.view.home.HomeFragment
import com.example.sportspot.view.news.NewsFragment
import com.example.sportspot.view.profile.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService
    private lateinit var userPreferences: UserPreferences // Initialize UserPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> fragment(HomeFragment())
                R.id.navigation_booking -> fragment(BookingFragment())
                R.id.navigation_news -> fragment(NewsFragment())
                R.id.navigation_profile -> fragment(ProfileFragment())
                else -> {
                }
            }
            true
        }
    }
    private fun fragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transitionFragment = fragmentManager.beginTransaction()
        transitionFragment.replace(R.id.frameLayout, fragment)
        transitionFragment.commit()
    }
}
