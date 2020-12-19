package com.brunocamacho.covid19news.domain

class GetNews(val repository: NewsRepository) {

    fun invoke(): MutableList<News> {
        return repository.findAll()
    }
}