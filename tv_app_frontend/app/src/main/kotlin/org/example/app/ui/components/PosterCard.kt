package org.example.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import org.example.app.data.MediaItem
import org.example.app.tv.tvFocus

// PUBLIC_INTERFACE
@Composable
fun PosterCard(
    item: MediaItem,
    modifier: Modifier = Modifier,
    onClick: (MediaItem) -> Unit
) {
    val shape = RoundedCornerShape(10.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .clickable { onClick(item) }
            .tvFocus()
            .background(
                brush = Brush.verticalGradient(
                    0f to Color(0xFF1C2330),
                    1f to Color(0xFF0F141A)
                )
            )
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .background(Color(0x66000000))
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
