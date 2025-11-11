package org.example.app.ui.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.example.app.data.TabItem
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.wrapContentHeight

// PUBLIC_INTERFACE
@Composable
fun TopBarTabs(
    tabs: List<TabItem>,
    onSelected: (TabItem) -> Unit
) {
    var selected by remember { mutableStateOf(tabs.firstOrNull()) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .focusGroup()
    ) {
        tabs.forEach { tab ->
            val isSelected = selected?.id == tab.id
            Text(
                text = tab.title,
                style = if (isSelected) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFFB0BEC5),
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            selected = tab
                            onSelected(tab)
                        },
                        role = Role.Tab
                    )
            )
        }
    }
}
