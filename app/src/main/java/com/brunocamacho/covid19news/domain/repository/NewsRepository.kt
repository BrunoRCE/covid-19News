package com.brunocamacho.covid19news.domain.repository

import com.brunocamacho.covid19news.domain.entity.News

interface NewsRepository {

    fun findAll(): MutableList<News>
}