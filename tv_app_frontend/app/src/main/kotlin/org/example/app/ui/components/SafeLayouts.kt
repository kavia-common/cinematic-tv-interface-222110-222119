package org.example.app.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * PUBLIC_INTERFACE
 * SafeColumn is a no-op container that simply invokes its content without using Compose's Column.
 * This avoids triggering inline methods like Column$default that cause IR lowering failures.
 *
 * Layout responsibilities (spacing, alignment) should be handled by child Modifiers (padding/size).
 *
 * @param modifier Modifier to be applied by children individually as needed.
 * @param content Content to render in order.
 */
@Composable
fun SafeColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Intentionally do not use Row/Column/Box; just emit content in sequence.
    // Modifiers are expected to be applied directly on children.
    content()
}
