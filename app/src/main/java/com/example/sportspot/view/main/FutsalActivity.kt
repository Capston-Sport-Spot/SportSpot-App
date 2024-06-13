package com.example.sportspot.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportspot.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.recyclerview.widget.RecyclerView

class FutsalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futsal)

        val nameEditTextLayout: TextInputLayout = findViewById(R.id.nameEditTextLayout)
        val nameEditText: TextInputEditText = findViewById(R.id.nameEditText)
        val recyclerviewRecommendation: RecyclerView = findViewById(R.id.recyclerview_recommendation)

        // Set up your RecyclerView and other views here
    }
}