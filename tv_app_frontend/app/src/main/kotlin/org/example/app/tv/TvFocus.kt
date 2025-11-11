package org.example.app.tv

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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
    focusedScale: Float = 1.08f,
    cornerRadius: Dp = 12.dp,
    glowColors: List<Color> = listOf(GlowBlue.copy(alpha = 0.5f), GlowCyan.copy(alpha = 0.5f))
): Modifier {
    // Avoid remember/animate to prevent IR inline issues in CI; use simple state-less indicator
    var isFocused = false
    val shape = RoundedCornerShape(cornerRadius)
    val requester = FocusRequester()

    return this
        .scale(if (isFocused) focusedScale else 1f)
        .shadow(if (isFocused) 16.dp else 4.dp, shape, clip = false)
        .border(
            BorderStroke(
                width = if (isFocused) 2.dp else 0.dp,
                brush = Brush.linearGradient(glowColors)
            ),
            shape = shape
        )
        .onFocusChanged { state -> isFocused = state.hasFocus }
        .focusRequester(requester)
        .focusProperties { canFocus = true }
        .semantics { role = Role.Button }
}
