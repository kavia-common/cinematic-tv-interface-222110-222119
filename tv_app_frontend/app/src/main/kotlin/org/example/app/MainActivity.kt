package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import org.example.app.ui.navigation.AppNavGraph
import org.example.app.ui.theme.AppTheme

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
    // Delegate layout and any backgrounds to screens to avoid top-level inline Box/Column calls.
    AppNavGraph(navController = navController)
}
