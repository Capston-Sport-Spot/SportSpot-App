package com.example.sportspot.view.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sportspot.R

class FieldDetail : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapangan_detail)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imageImageView = findViewById<ImageView>(R.id.imageImageView)

        val name = intent.getStringExtra("name")
        val deskripsi = intent.getStringExtra("deskripsi")
        val image = intent.getStringExtra("image")

        nameTextView.text = name
        descriptionTextView.text = deskripsi
        Glide.with(this).load(image).into(imageImageView)
    }
}
