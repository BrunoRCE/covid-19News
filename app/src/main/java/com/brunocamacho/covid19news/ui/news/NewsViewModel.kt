package com.brunocamacho.covid19news.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brunocamacho.covid19news.domain.entity.News
import com.brunocamacho.covid19news.domain.useCase.GetNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private var getNews: GetNews) : ViewModel() {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    fun getData() {
        viewModelScope.launch {
            _progress.value = true
            _news.value = withContext(Dispatchers.IO) {
                getNews.invoke()
            }
            _progress.value = false
        }
    }
}