package org.example.app.tv

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.app.ui.theme.GlowBlue
import org.example.app.ui.theme.GlowCyan

// PUBLIC_INTERFACE
fun Modifier.tvFocus(
    focusedScale: Float = 1.0f, // keep static to avoid dynamic focus-dependent IR paths
    cornerRadius: Dp = 12.dp,
    glowColors: List<Color> = listOf(GlowBlue.copy(alpha = 0.5f), GlowCyan.copy(alpha = 0.5f))
): Modifier {
    val shape = RoundedCornerShape(cornerRadius)

    // Static styling: gentle shadow and border; no focusRequester or focusProperties to avoid IR issues.
    return this
        .scale(focusedScale)
        .shadow(4.dp, shape, clip = false)
        .border(
            BorderStroke(
                width = 1.dp,
                brush = Brush.linearGradient(glowColors)
            ),
            shape = shape
        )
        .semantics { role = Role.Button }
}
