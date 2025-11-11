package org.example.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
/* no custom Layout import; avoid unresolved reference and IR inlining issues */
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.example.app.data.MediaItem
import org.example.app.tv.tvFocus

/**
 * PUBLIC_INTERFACE
 * PosterCard shows a media poster with a gradient title overlay.
 * Eliminates direct Box usage to avoid Box$default inlining, and precomputes shapes to avoid
 * RoundedCornerShape(...) inline intrinsic paths that were observed to trigger IR issues.
 *
 * @param item MediaItem to display.
 * @param modifier Modifier applied to the card container.
 * @param onClick Callback when the card is clicked.
 */
@Composable
fun PosterCard(
    item: MediaItem,
    modifier: Modifier = Modifier,
    onClick: (MediaItem) -> Unit
) {
    // Precompute commonly used Dp values and shapes to avoid repeated inline calls at call sites.
    val cardHeight: Dp = 180.dp
    val cornerRadius: Dp = 12.dp
    // Avoid remember to prevent IR inline of ComposablesKt.remember; these are cheap and constant.
    val shape = RoundedCornerShape(cornerRadius)
    val gradientBrush = Brush.verticalGradient(listOf(Color.Transparent, Color(0x99000000)))

    val baseModifier = modifier
        .clip(shape)
        .clickable { onClick(item) }
        .tvFocus(cornerRadius = cornerRadius)
        .background(Color(0xFF141414))
        .height(cardHeight)
        .fillMaxWidth()

    val painter = rememberAsyncImagePainter(model = item.imageUrl)

    // Compose in sequence: Image, gradient strip at bottom area, then title.
    // We avoid Box by relying on the fact that later children draw after earlier ones.
    Image(
        painter = painter,
        contentDescription = item.title,
        modifier = baseModifier,
        contentScale = ContentScale.Crop
    )

    // A bottom-aligned gradient strip effect using a fixed-height gradient and padding for title.
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(brush = gradientBrush)
    )

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
