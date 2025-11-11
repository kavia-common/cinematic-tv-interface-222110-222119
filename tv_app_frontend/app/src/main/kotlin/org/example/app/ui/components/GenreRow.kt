package org.example.app.ui.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.data.MediaItem

/**
 * PUBLIC_INTERFACE
 * GenreRow renders a non-animated, stateless horizontal row of poster cards for a given genre title.
 * This implementation intentionally avoids remember/AnimatedVisibility or any inline-heavy composables
 * to mitigate Kotlin IR inlining crashes observed in some Docker/CI environments.
 *
 * Parameters:
 * - title: String - Row title displayed above posters.
 * - items: List<MediaItem> - Items to render as posters.
 * - onCardClick: (MediaItem) -> Unit - Callback when a poster is clicked.
 */
@Composable
fun GenreRow(
    title: String,
    items: List<MediaItem>,
    onCardClick: (MediaItem) -> Unit
) {
    // Title text without animations
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )

    // Stateless LazyRow without using inline helpers that trigger IR inlining (avoid items/itemsIndexed)
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .focusGroup()
    ) {
        // Compose all children within a single item to bypass inline extension helpers entirely
        item {
            for (index in 0 until items.size) {
                val media = items[index]
                PosterCard(
                    item = media,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(width = 140.dp, height = 180.dp),
                    onClick = onCardClick
                )
            }
        }
    }

    // Spacer to separate rows, not animated
    Spacer(modifier = Modifier.height(12.dp))
}
