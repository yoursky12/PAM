package com.example.tugaspraktikum2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository = NewsRepository()
) {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val _selectedCategory = MutableStateFlow("Semua")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _newsFeed = MutableStateFlow<List<String>>(emptyList())
    val newsFeed: StateFlow<List<String>> = _newsFeed.asStateFlow()

    init {
        observeNewsStream()
    }

    private fun observeNewsStream() {
        scope.launch {
            repository.getNewsStream()
                .filter { item ->
                    _selectedCategory.value == "Semua" || item.category == _selectedCategory.value
                }
                .map { item ->
                    "[${item.category.uppercase()}] ${item.title} - (Baru saja)"
                }
                .onEach { formattedItem ->
                    println("Memproses: $formattedItem")
                }
                .catch { e ->
                    println("Error Flow: ${e.message}")
                }
                .collect { formattedTitle ->
                    _newsFeed.value = listOf(formattedTitle) + _newsFeed.value
                }
        }
    }

    fun setCategoryFilter(category: String) {
        _selectedCategory.value = category
    }

    fun markAsRead(newsId: Int, onDetailLoaded: (String) -> Unit) {
        _readCount.value += 1
        scope.launch {
            val detail = repository.fetchNewsDetailAsync(newsId)
            onDetailLoaded(detail)
        }
    }
}