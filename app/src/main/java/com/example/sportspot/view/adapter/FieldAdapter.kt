package com.example.sportspot.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportspot.databinding.ItemRowBinding
import com.example.sportspot.response.FieldResponseItem
import com.example.sportspot.view.detail.DetailFieldActivity


class FieldAdapter : ListAdapter<FieldResponseItem, FieldAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val field = getItem(position)
        holder.bind(field)
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lapangan: FieldResponseItem) {
            with(binding) {
                tvName.text = lapangan.lapanganName
                tvLocation.text = lapangan.kota
                Glide.with(itemView.context)
                    .load(lapangan.imageUrl)
                    .into(ivAvatar)
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailFieldActivity::class.java)
                    intent.putExtra(DetailFieldActivity.DETAIL_FIELD, lapangan)
                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(ivAvatar, "profile"),
                            Pair(tvName, "name"),
                            Pair(tvLocation, "kota")
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FieldResponseItem>() {
            override fun areItemsTheSame(
                oldItem: FieldResponseItem,
                newItem: FieldResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FieldResponseItem,
                newItem: FieldResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}