package com.brunocamacho.covid19news.ui.news

import com.brunocamacho.covid19news.domain.News

interface NewsView {

    fun showProgress(show: Boolean)

    fun setList(list: MutableList<News>)

    fun showError(error: String)
}