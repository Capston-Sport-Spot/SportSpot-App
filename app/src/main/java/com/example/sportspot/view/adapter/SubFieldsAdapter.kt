package com.example.sportspot.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportspot.R
import com.example.sportspot.response.SubFieldsItem

class SubFieldsAdapter(private val subFields: List<SubFieldsItem?>) : RecyclerView.Adapter<SubFieldsAdapter.SubFieldsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubFieldsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_field, parent, false)
        return SubFieldsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubFieldsViewHolder, position: Int) {
        val subField = subFields[position]
        holder.bind(subField)
    }

    override fun getItemCount(): Int = subFields.size

    inner class SubFieldsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFieldName: TextView = itemView.findViewById(R.id.tvFieldName)
        private val tvFacilities: TextView = itemView.findViewById(R.id.tvFacilities)
        private val tvPricePerSession: TextView = itemView.findViewById(R.id.tvPricePerSession)

        fun bind(subField: SubFieldsItem?) {
            tvFieldName.text = subField?.fieldName ?: "N/A"
            tvFacilities.text = subField?.facilities?.joinToString(", ") ?: "N/A"
            tvPricePerSession.text = subField?.pricePerSession?.toString() ?: "N/A"
        }
    }
}
