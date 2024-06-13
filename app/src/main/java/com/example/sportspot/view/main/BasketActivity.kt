package com.example.sportspot.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportspot.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.recyclerview.widget.RecyclerView

class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        val nameEditTextLayout = findViewById<TextInputLayout>(R.id.nameEditTextLayout)
        val nameEditText = findViewById<TextInputEditText>(R.id.nameEditText)
        val recyclerviewRecommendation = findViewById<RecyclerView>(R.id.recyclerview_recommendation)

        // Set up your RecyclerView and other views here
    }
}
