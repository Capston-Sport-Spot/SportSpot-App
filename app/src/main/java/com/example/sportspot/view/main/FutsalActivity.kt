package com.example.sportspot.view.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sportspot.R
import com.example.sportspot.api.ApiService
import com.example.sportspot.response.FieldResponse
import com.example.sportspot.view.adapter.FieldAdapter
import com.example.sportspot.view.adapter.FieldDetail
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.emptyList as emptyList


class FutsalActivity : AppCompatActivity() {

    private lateinit var searchEditText: TextInputEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var fieldAdapter: FieldAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futsal)

        searchEditText = findViewById(R.id.searchEditText)
        recyclerView = findViewById(R.id.recyclerview_recommendation)
        val backButton: ImageButton = findViewById(R.id.button_back)

        recyclerView.layoutManager = LinearLayoutManager(this)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
//                    searchField(query)
                }
            }
        })

        backButton.setOnClickListener {
            finish()
        }
    }

//    private fun searchField(query: String) {
//        ApiService.searchField(query, "query").enqueue(object : Callback<FieldResponse> {
//            override fun onResponse(call: Call<FieldResponse>, response: Response<FieldResponse>) {
//                if (response.isSuccessful) {
//                    val lapanganList = response.body()?.message ?: emptyList<Any>()
//                    fieldAdapter = FieldAdapter(lapanganList) { lapangan ->
//                        val intent = Intent(this@FutsalActivity, FieldDetail::class.java).apply {
//                            putExtra("name", lapangan.name)
//                            putExtra("description", lapangan.description)
//                            putExtra("image", lapangan.image)
//                        }
//                        startActivity(intent)
//                    }
//                    recyclerView.adapter = fieldAdapter
//                }
//            }
//
//            override fun onFailure(call: Call<FieldResponse>, t: Throwable) {
//                // Handle failure
//            }
//        })
//    }
//
//    // Contoh penggunaan searchEditText di luar onCreate
//    private fun exampleFunction() {
//        val searchText = searchEditText.text.toString()
//        // Lakukan sesuatu dengan searchText
//    }
}

