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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppNavGraph(navController = navController)
    }
}
