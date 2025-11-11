package org.example.app.ui.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.data.MediaItem
import androidx.compose.foundation.layout.Spacer

// PUBLIC_INTERFACE
@Composable
fun GenreRow(
    title: String,
    items: List<MediaItem>,
    onCardClick: (MediaItem) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )

    // Simple, non-animated row to avoid inline IR crashes from AnimatedVisibility/remember
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .focusGroup()
    ) {
        items(items) { item ->
            PosterCard(
                item = item,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(width = 140.dp, height = 180.dp),
                onClick = onCardClick
            )
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
}
