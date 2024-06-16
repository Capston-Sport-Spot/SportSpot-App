package com.example.sportspot.view.main

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.sportspot.R

class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        val backButton: ImageButton = findViewById(R.id.button_back)

        backButton.setOnClickListener {
            finish()
        }

    }
}
