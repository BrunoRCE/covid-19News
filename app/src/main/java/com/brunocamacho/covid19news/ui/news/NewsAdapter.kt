package com.brunocamacho.covid19news.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brunocamacho.covid19news.R
import com.brunocamacho.covid19news.domain.News

class NewsAdapter(news: MutableList<News>, clickListener: ClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val news: MutableList<News> = news
    private val clickListener: ClickListener = clickListener

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setNews(news[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(view: View, clickListener: ClickListener) :
        RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.url)
        private val info: TextView = view.findViewById(R.id.note)
        private val share: ImageButton = view.findViewById(R.id.share)

        init {
            view.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

        fun setNews(news: News) {
            title.text = news.title
            info.text = news.info
            share.setOnClickListener {
                clickListener.onShareItemClick(adapterPosition)
            }
        }
    }

    interface ClickListener {
        fun onItemClick(position: Int)

        fun onShareItemClick(position: Int)
    }
}