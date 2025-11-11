package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import org.example.app.ui.navigation.AppNavGraph
import org.example.app.ui.theme.AppTheme
import org.example.app.ui.theme.GradientEnd
import org.example.app.ui.theme.GradientStart

// PUBLIC_INTERFACE
class MainActivity : ComponentActivity() {
    /**
     * The entry point Activity that hosts the Jetpack Compose UI.
     * Applies a Material 3 dark theme and sets up the NavGraph for Home and Details screens.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                RootContent()
            }
        }
    }
}

@Composable
private fun RootContent() {
    val navController = rememberNavController()

    // Apply gradient background by drawing behind the content to avoid Box/Layout inline methods
    val gradient = Brush.verticalGradient(colors = listOf(GradientStart, GradientEnd))
    AppNavGraph(
        navController = navController
    ).let {
        // Attach modifier via CompositionLocal trick is not applicable; instead wrap in a top-level containerless modifier:
        // Compose requires a Composable to apply a modifier; since AppNavGraph is the root, we inject a no-op container:
        androidx.compose.runtime.CompositionLocalProvider {
            // draw the gradient behind by placing a full-screen draw layer before content
            // We use two separate composition passes: background and then content.
            // Background layer:
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(brush = gradient, topLeft = Offset.Zero, size = this.size)
                    }
            )
            // Content layer:
            AppNavGraph(navController = navController)
        }
    }
}
