package com.example.sportspot.view.main

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}
