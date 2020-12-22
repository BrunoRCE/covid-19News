package com.brunocamacho.covid19news.domain.useCase

import com.brunocamacho.covid19news.domain.entity.News
import com.brunocamacho.covid19news.domain.repository.NewsRepository

class GetNews(val repository: NewsRepository) {

    fun invoke(): MutableList<News> {
        return repository.findAll()
    }
}