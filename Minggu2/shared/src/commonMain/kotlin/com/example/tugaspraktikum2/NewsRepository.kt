package com.example.tugaspraktikum2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository {

    fun getNewsStream(): Flow<NewsItem> = flow {
        val categories = listOf("Teknologi", "Olahraga", "Politik", "Hiburan")
        var id = 1

        while (true) {
            val randomCategory = categories.random()
            val news = NewsItem(
                id = id,
                title = "Berita Utama #$id tentang $randomCategory",
                category = randomCategory
            )
            emit(news)
            id++
            delay(2000)
        }
    }

    suspend fun fetchNewsDetailAsync(newsId: Int): String = coroutineScope {
        val deferredDetail = async(Dispatchers.IO) {
            delay(1000)
            "Detail lengkap isi berita untuk ID #$newsId berhasil dimuat."
        }
        deferredDetail.await()
    }
}