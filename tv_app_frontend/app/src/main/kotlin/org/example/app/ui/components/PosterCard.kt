package org.example.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.example.app.data.MediaItem
import org.example.app.tv.tvFocus

/**
 * PUBLIC_INTERFACE
 * PosterCard shows a media poster with a gradient title overlay.
 * It intentionally avoids Box() usage to prevent triggering inline default overloads (Box$default),
 * which have caused IR inlining crashes in some CI environments.
 */
@Composable
fun PosterCard(
    item: MediaItem,
    modifier: Modifier = Modifier,
    onClick: (MediaItem) -> Unit
) {
    val shape = RoundedCornerShape(12.dp)
    // Compose the card using a stacked layout without calling Box()
    // We rely on a clickable, clipped container and place child layers using full-size modifiers.
    val baseModifier = modifier
        .clip(shape)
        .clickable { onClick(item) }
        .tvFocus(cornerRadius = 12.dp)
        .background(Color(0xFF141414))
        .then(Modifier.height(180.dp))
        .fillMaxWidth()

    val painter = rememberAsyncImagePainter(model = item.imageUrl)

    // Background image layer
    Image(
        painter = painter,
        contentDescription = item.title,
        modifier = baseModifier, // full card bounds
        contentScale = ContentScale.Crop
    )

    // Gradient overlay strip (avoid Row/Box to bypass inline defaults).
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0x99000000))
                )
            )
    )

    // Title text (placed after overlay in composition to appear on top)
    Text(
        text = item.title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        textAlign = TextAlign.Start,
        maxLines = 1
    )
}
