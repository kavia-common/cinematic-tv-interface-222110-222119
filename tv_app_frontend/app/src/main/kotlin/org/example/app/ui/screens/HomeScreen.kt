package org.example.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.app.data.GenreRowData
import org.example.app.data.MediaItem
import org.example.app.data.TabItem
import org.example.app.ui.components.GenreRow
import org.example.app.ui.components.TopBarTabs

// PUBLIC_INTERFACE
@Composable
fun HomeScreen(
    tabs: List<TabItem>,
    featured: MediaItem,
    genreRows: List<GenreRowData>,
    onCardClick: (MediaItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0f to Color(0xFF0B0F14),
                    1f to Color(0xFF0F141A)
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(bottom = 48.dp)
    ) {
        TopBarTabs(tabs = tabs, onSelected = { /* No-op for static data; could filter */ })

        FeaturedBanner(featured)

        Spacer(modifier = Modifier.height(12.dp))

        genreRows.forEach { row ->
            GenreRow(
                title = row.title,
                items = row.items,
                onCardClick = onCardClick
            )
        }
    }
}

@Composable
private fun FeaturedBanner(item: MediaItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(Color(0xFF1C2330), Color(0xFF0F141A))
                ),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )
    }
}
