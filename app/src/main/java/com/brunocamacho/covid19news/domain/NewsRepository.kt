package com.brunocamacho.covid19news.domain

interface NewsRepository {

    fun findAll(): MutableList<News>
}