package com.example.sportspot.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sportspot.R
import com.example.sportspot.preferences.FieldModel

class FieldAdapter(private val fieldList: List<FieldModel>, private val onItemClick: (FieldModel) -> Unit) : RecyclerView.Adapter<FieldAdapter.FieldViewHolder>() {

    class FieldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return FieldViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        val lapangan = fieldList[position]
        val drawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.img_basket)
        holder.ivAvatar.setImageDrawable(drawable)
        holder.tvJudul.text = lapangan.name
        holder.tvDeskripsi.text = lapangan.description
        holder.itemView.setOnClickListener {
            onItemClick(lapangan)
        }
    }

    override fun getItemCount() = fieldList.size
}