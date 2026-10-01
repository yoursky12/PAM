package com.example.tugaspraktikum2

data class NewsItem(
    val id: Int,
    val title: String,
    val category: String,
    val content: String = ""
)