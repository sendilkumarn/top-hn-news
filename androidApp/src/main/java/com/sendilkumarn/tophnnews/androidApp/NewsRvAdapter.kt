package com.sendilkumarn.tophnnews.androidApp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sendilkumarn.tophnnews.shared.entity.NewsItem

class NewsRvAdapter(var newsItems: List<NewsItem>) : RecyclerView.Adapter<NewsRvAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
            .run(::NewsViewHolder)
    }

    override fun getItemCount() = newsItems.count()

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(newsItems[position])
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.title)
        private val authorTextView = itemView.findViewById<TextView>(R.id.author)

        fun bindData(launch: NewsItem) {
            val ctx = itemView.context
            titleTextView.text = ctx.getString(R.string.title_field, launch.title)
            authorTextView.text = ctx.getString(R.string.author_field, launch.author)

            itemView.setOnClickListener {
                val webpage = Uri.parse(launch.url)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(ctx, intent, null)
            }
        }
    }
}
