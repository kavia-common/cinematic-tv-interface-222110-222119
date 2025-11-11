package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import org.example.app.ui.theme.AppTheme
import org.example.app.ui.navigation.AppNavGraph
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Brush
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

    // Use a simple non-inline background wrapper to avoid IR inliner issues
    BackgroundContainer {
        AppNavGraph(navController = navController)
    }
}

// PUBLIC_INTERFACE
@Composable
private fun BackgroundContainer(content: @Composable () -> Unit) {
    // Build the gradient and apply as Modifier without using inline Box/Column helpers
    val bgBrush = Brush.verticalGradient(listOf(GradientStart, GradientEnd))
    // Compose allows invoking content() directly at the root
    androidx.compose.runtime.CompositionLocalProvider {
        // Apply background to a zero-layout wrapper using drawBehind via background modifier on an empty layout
        // Use Spacer to realize the modifier chain without relying on Box/Column inline API
        androidx.compose.foundation.layout.Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(bgBrush)
        )
        content()
    }
}
