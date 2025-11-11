package org.example.app.tv

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.focusable as tvFocusable

// PUBLIC_INTERFACE
fun Modifier.tvFocus(
    focusedScale: Float = 1.08f,
    cornerRadius: Dp = 10.dp,
    glowColors: List<Color> = listOf(Color(0x333B82F6), Color(0x3306B6D4))
): Modifier = composed {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (focused) focusedScale else 1f, label = "focus-scale")

    this
        .scale(scale)
        .border(
            BorderStroke(
                width = if (focused) 2.dp else 0.dp,
                brush = Brush.linearGradient(glowColors)
            ),
            shape = RoundedCornerShape(cornerRadius)
        )
        .tvFocusable(true) { isFocused ->
            focused = isFocused
        }
        .focusable(interactionSource = remember { MutableInteractionSource() })
}
