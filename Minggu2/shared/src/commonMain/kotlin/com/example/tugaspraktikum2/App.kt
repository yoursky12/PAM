package com.example.tugaspraktikum2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = remember { NewsViewModel() }
        var selectedDetailText by remember { mutableStateOf<String?>(null) }

        val newsList by viewModel.newsFeed.collectAsState()
        val readCount by viewModel.readCount.collectAsState()
        val selectedCategory by viewModel.selectedCategory.collectAsState()

        val categories = listOf("Semua", "Teknologi", "Olahraga", "Politik", "Hiburan")

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "News Feed Simulator",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Berita Dibaca: $readCount",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.take(3).forEach { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { viewModel.setCategoryFilter(cat) },
                        label = { Text(cat) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            selectedDetailText?.let { detail ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    Text(
                        text = detail,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(newsList) { index, item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.markAsRead(index + 1) { detailText ->
                                    selectedDetailText = detailText
                                }
                            }
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}