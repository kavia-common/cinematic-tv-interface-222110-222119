package org.example.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.example.app.data.GenreRowData
import org.example.app.data.MediaItem
import org.example.app.data.TabItem
import org.example.app.ui.components.GenreRow
import org.example.app.ui.components.TopBarTabs
import org.example.app.ui.theme.GradientEnd
import org.example.app.ui.theme.GradientStart
import org.example.app.ui.theme.TealAccent

// PUBLIC_INTERFACE
@Composable
fun HomeScreen(
    tabs: List<TabItem>,
    featured: MediaItem,
    genreRows: List<GenreRowData>,
    onCardClick: (MediaItem) -> Unit
) {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = listOf(GradientStart, GradientEnd), startY = 0f, endY = Float.POSITIVE_INFINITY)
            )
            .verticalScroll(state = scroll)
            .padding(bottom = 48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TopBarTabs(tabs = tabs, onSelected = { /* Could navigate or filter */ })

        FeaturedBanner(item = featured, scrollOffset = scroll.value)

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

/**
 * Featured Banner showing the Interstellar item.
 * Includes a dark overlay, title, description and a Play Now button accented with #00BCD4.
 * Adds subtle parallax based on vertical scroll offset.
 */
// PUBLIC_INTERFACE
@Composable
fun FeaturedBanner(item: MediaItem, scrollOffset: Int) {
    // Subtle parallax based on scroll
    val parallaxOffset = (-scrollOffset / 40f).coerceAtLeast(-20f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clip(MaterialTheme.shapes.large),
        propagateMinConstraints = false
    ) {
        val painter = rememberAsyncImagePainter(model = item.imageUrl)
        Image(
            painter = painter,
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = parallaxOffset.dp),
            contentScale = ContentScale.Crop
        )
        // Dark overlay for readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0x99000000), Color(0xCC000000)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFCFD8DC),
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /* TODO: play */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = TealAccent,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Play Now")
            }
        }
    }
}
