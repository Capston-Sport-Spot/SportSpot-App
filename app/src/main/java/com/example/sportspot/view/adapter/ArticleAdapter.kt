package com.example.sportspot.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportspot.R
import com.example.sportspot.response.ArticleResponseItem

class ArticleAdapter(private val articles: List<ArticleResponseItem?>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private lateinit var onItemCallback : OnItemClickCallback
    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewTitle: TextView = view.findViewById(R.id.tv_article_name)
        val textViewTime: TextView = view.findViewById(R.id.tv_article_desc)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.textViewTitle.text = article?.title
        holder.textViewTime.text = article?.time
        // Load image using Glide or Picasso
        Glide.with(holder.itemView.context).load(article?.imageLink).into(holder.imageView)
    }
    interface OnItemClickCallback{
        fun onItemClicked(data : ArticleResponseItem)
    }

    override fun getItemCount(): Int = articles.size
}