package com.brunocamacho.covid19news.ui.news

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunocamacho.covid19news.data.NewsJsoup
import com.brunocamacho.covid19news.databinding.NewsActivityBinding
import com.brunocamacho.covid19news.domain.GetNews
import com.brunocamacho.covid19news.domain.News
import com.brunocamacho.covid19news.ui.note.NoteActivity

class NewsActivity : AppCompatActivity(), NewsView, NewsAdapter.ClickListener {

    private lateinit var binding: NewsActivityBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var news: MutableList<News>
    private lateinit var presenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NewsActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        news = mutableListOf()
        adapter = NewsAdapter(news, this)

        binding.list.adapter = adapter
        binding.list.setHasFixedSize(true)
        binding.list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.refresh.setOnRefreshListener {
            presenter.onCreate()
        }

        presenter = NewsPresenter(this, GetNews(NewsJsoup()))
        presenter.onCreate()
    }

    override fun showProgress(show: Boolean) {
        binding.refresh.isRefreshing = show
    }

    override fun setList(news: MutableList<News>) {
        this.news.clear()
        this.news.addAll(news)
        adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int) {
        startActivity(
            Intent(this, NoteActivity::class.java)
                .putExtra("url", news[position].url)
        )
    }

    override fun onShareItemClick(position: Int) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, news[position].url)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, ""))
    }
}