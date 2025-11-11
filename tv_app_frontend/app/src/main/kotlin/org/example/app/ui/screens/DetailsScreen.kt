package org.example.app.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
/* Avoid importing Column/Row/Arrangement to prevent inline defaults during IR. */
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import org.example.app.data.MediaItem
import org.example.app.ui.theme.GradientEnd
import org.example.app.ui.theme.GradientStart
import org.example.app.ui.theme.TealAccent

// PUBLIC_INTERFACE
@Composable
fun DetailsScreen(
    item: MediaItem?,
    onBack: () -> Unit
) {
    BackHandler { onBack() }

    // Sequential composition without Column/Row to avoid inline IR issues.
    val containerModifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(GradientStart, GradientEnd),
                startY = 0f,
                endY = Float.POSITIVE_INFINITY
            )
        )
        .padding(all = 24.dp)

    // Title
    Text(
        text = item?.title ?: "Unknown",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = containerModifier
    )

    // Description
    Text(
        text = item?.description ?: "No description available.",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 24.dp, end = 24.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Actions (rendered sequentially)
    Button(onClick = onBack) { Text("Back") }
    Spacer(modifier = Modifier.height(12.dp))
    Button(
        onClick = { /* play action placeholder */ },
        colors = ButtonDefaults.buttonColors(containerColor = TealAccent)
    ) { Text("Play") }
}
