package com.brunocamacho.covid19news.ui.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunocamacho.covid19news.R
import com.brunocamacho.covid19news.data.NewsJsoup
import com.brunocamacho.covid19news.databinding.NewsActivityBinding
import com.brunocamacho.covid19news.domain.entity.News
import com.brunocamacho.covid19news.domain.useCase.GetNews
import com.brunocamacho.covid19news.ui.note.NoteActivity

class NewsActivity : AppCompatActivity(), NewsAdapter.ClickListener {

    private lateinit var binding: NewsActivityBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var news: MutableList<News>
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NewsActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        news = mutableListOf()
        adapter = NewsAdapter(news, this)

        binding.list.adapter = adapter
        binding.list.setHasFixedSize(true)
        binding.list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = NewsViewModel(GetNews(NewsJsoup()))
        viewModel.getData()

        binding.refresh.setOnRefreshListener {
            viewModel.getData()
        }

        viewModel.progress.observe(this, { visibility ->
            binding.refresh.isRefreshing = visibility
        })

        viewModel.news.observe(this, { news ->
            this.news.clear()
            this.news.addAll(news)
            adapter.notifyDataSetChanged()
        })
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

        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }
}