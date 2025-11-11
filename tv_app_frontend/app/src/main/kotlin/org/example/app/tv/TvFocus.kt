package org.example.app.tv

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.app.ui.theme.GlowBlue
import org.example.app.ui.theme.GlowCyan
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.Role

// PUBLIC_INTERFACE
fun Modifier.tvFocus(
    focusedScale: Float = 1.08f,
    cornerRadius: Dp = 12.dp,
    glowColors: List<Color> = listOf(GlowBlue.copy(alpha = 0.5f), GlowCyan.copy(alpha = 0.5f))
): Modifier {
    // Implement using stable focus APIs without composed{} so it compiles under strict imports.
    var focused by mutableStateOf(false)
    val scale by animateFloatAsState(
        targetValue = if (focused) focusedScale else 1f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 350f),
        label = "focus-scale"
    )
    val shape = RoundedCornerShape(cornerRadius)
    val requester = FocusRequester()
    val interaction = MutableInteractionSource()

    return this
        .scale(scale)
        .shadow(if (focused) 16.dp else 4.dp, shape, clip = false)
        .border(
            BorderStroke(
                width = if (focused) 2.dp else 0.dp,
                brush = Brush.linearGradient(glowColors)
            ),
            shape = shape
        )
        .onFocusChanged { state -> focused = state.hasFocus }
        // Use focusRequester + semantics role to enhance TV focus behavior
        .focusRequester(requester)
        .focusProperties { canFocus = true }
        // Mark as a generic button-like role for DPAD
        .semantics { role = Role.Button }
}
