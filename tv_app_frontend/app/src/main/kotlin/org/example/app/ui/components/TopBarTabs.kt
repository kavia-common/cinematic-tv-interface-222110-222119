package org.example.app.ui.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.app.data.TabItem

/**
 * PUBLIC_INTERFACE
 * TopBarTabs renders a simple title and a flat list of tabs without using remember/Column/Row
 * to avoid triggering inline default overloads that cause IR issues in CI.
 *
 * Selection is communicated outward via onSelected; no internal Compose state is kept.
 */
@Composable
fun TopBarTabs(
    tabs: List<TabItem>,
    onSelected: (TabItem) -> Unit
) {
    // Title
    Text(
        text = "🎬 Cinematic TV",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    )

    // Tabs rendered as a simple vertical list with spacing; avoid Row/Column
    val padding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
    tabs.forEachIndexed { index, tab ->
        Text(
            text = tab.title,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFB0BEC5),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = if (index == 0) 8.dp else 4.dp, bottom = 4.dp)
                .focusGroup()
                .selectable(
                    selected = false,
                    onClick = { onSelected(tab) },
                    role = Role.Tab
                )
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
