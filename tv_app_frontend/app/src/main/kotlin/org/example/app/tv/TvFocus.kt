package org.example.app.tv

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.composed
import org.example.app.ui.theme.GlowBlue
import org.example.app.ui.theme.GlowCyan

// PUBLIC_INTERFACE
fun Modifier.tvFocus(
    focusedScale: Float = 1.08f,
    cornerRadius: Dp = 12.dp,
    glowColors: List<Color> = listOf(GlowBlue.copy(alpha = 0.5f), GlowCyan.copy(alpha = 0.5f))
): Modifier = composed {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (focused) focusedScale else 1f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 350f),
        label = "focus-scale"
    )

    this
        .scale(scale)
        .shadow(if (focused) 16.dp else 4.dp, RoundedCornerShape(cornerRadius), clip = false)
        .border(
            BorderStroke(
                width = if (focused) 2.dp else 0.dp,
                brush = Brush.linearGradient(glowColors)
            ),
            shape = RoundedCornerShape(cornerRadius)
        )
        .onFocusChanged { state -> focused = state.hasFocus }
        .focusable(
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )
}
