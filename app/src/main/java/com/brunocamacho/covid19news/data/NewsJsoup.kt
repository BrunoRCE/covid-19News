package com.brunocamacho.covid19news.data

import com.brunocamacho.covid19news.domain.entity.News
import com.brunocamacho.covid19news.domain.repository.NewsRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsJsoup : NewsRepository {

    private fun getIndexInfo(list: List<String>): Int {
        var index = list.lastIndex

        while (!list.get(index).contains("-")) {
            index--
            if (index < 0) {
                index = 0
                break
            }
        }

        return index
    }

    private fun capitalizeText(text: String): String {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private fun removeDashes(text: String): String {
        return text.replace("-", " ")
    }

    override fun findAll(): MutableList<News> {

        val url = "https://www.google.com/search?q=covid&tbm=nws"

        val news: MutableList<News> = mutableListOf()

        val document: Document = Jsoup.connect(url).get()

        val elements = document.select("a")

        elements.forEach { element ->

            val url = element.attr("href")

            if (!url.contains("google") && url.contains("http")) {

                val parts = url.split("/")

                news.add(
                    News(
                        url,
                        parts.get(2),
                        capitalizeText(removeDashes(parts.get(getIndexInfo(parts))))
                    )
                )
            }
        }

        return news
    }
}