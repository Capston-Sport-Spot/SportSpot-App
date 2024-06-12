package com.example.sportspot.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportspot.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle home navigation
                    true
                }
                R.id.navigation_event -> {
                    // Handle event navigation
                    true
                }
                R.id.navigation_news -> {
                    // Handle news navigation
                    true
                }
                R.id.navigation_profile -> {
                    // Handle profile
                    true
                }
                else -> false
            }
        }
    }
}
