package com.brunocamacho.covid19news.data

import com.brunocamacho.covid19news.domain.entity.News
import com.brunocamacho.covid19news.domain.repository.NewsRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsJsoup : NewsRepository {

    override fun findAll(): MutableList<News> {

        val url = "https://www.google.com/search?q=covid&tbm=nws"
        val document: Document = Jsoup.connect(url).get()

        val elements = document.select("a")

        val news: MutableList<News> = mutableListOf()

        elements.forEach { element ->

            val url = element.attr("href")

            if (!url.contains("google") && url.contains("http")) {

                val titles = url.split("/")

                var i = titles.lastIndex

                while (!titles.get(i).contains("-")) {
                    i--
                    if (i < 0) {
                        i = 0
                        break
                    }
                }

                news.add(
                    News(
                        url,
                        titles.get(2),
                        titles.get(i).replace("-"," ")
                    )
                )
            }
        }

        return news
    }
}