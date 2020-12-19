package com.brunocamacho.covid19news.ui.news

import com.brunocamacho.covid19news.domain.GetNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsPresenter(private val view: NewsView, private var getNews: GetNews) {

    fun onCreate() {
        view.showProgress(true)

        GlobalScope.launch {
            val list = getNews.invoke()

            withContext(Dispatchers.Main){
                view.showProgress(false)
                view.setList(list)
            }
        }
    }
}