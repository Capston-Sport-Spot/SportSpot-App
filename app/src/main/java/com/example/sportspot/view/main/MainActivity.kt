package com.example.sportspot.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sportspot.R
import com.example.sportspot.api.ApiService
import com.example.sportspot.databinding.ActivityMainBinding
import com.example.sportspot.preferences.UserPreferences
import com.example.sportspot.view.ViewModelFactory
import com.example.sportspot.view.article.ArticleFragment
import com.example.sportspot.view.booking.BookingFragment
import com.example.sportspot.view.home.HomeFragment
import com.example.sportspot.view.profile.ProfileFragment
import com.example.sportspot.view.welcome.WelcomeActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService
    private lateinit var userPreferences: UserPreferences // Initialize UserPreferences

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->

            if (!user.isLogin) {
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }

        fragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> fragment(HomeFragment())
                R.id.navigation_booking -> fragment(BookingFragment())
                R.id.navigation_article -> fragment(ArticleFragment())
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
