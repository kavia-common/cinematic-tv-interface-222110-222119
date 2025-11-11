package org.example.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.app.ui.screens.DetailsScreen
import org.example.app.ui.screens.HomeScreen
import org.example.app.data.SampleRepository

object Routes {
    const val Home = "home"
    const val Details = "details/{id}"

    fun details(id: String) = "details/$id"
}

// PUBLIC_INTERFACE
@Composable
fun AppNavGraph(navController: NavHostController) {
    val repo = SampleRepository()
    NavHost(navController = navController, startDestination = Routes.Home) {
        addHome(navController, repo)
        addDetails(navController, repo)
    }
}

// PUBLIC_INTERFACE
fun NavGraphBuilder.addHome(navController: NavController, repo: SampleRepository) {
    composable(Routes.Home) {
        HomeScreen(
            tabs = repo.tabs(),
            featured = repo.featured(),
            genreRows = repo.genreRows(),
            onCardClick = { item -> navController.navigate(Routes.details(item.id)) }
        )
    }
}

// PUBLIC_INTERFACE
fun NavGraphBuilder.addDetails(navController: NavController, repo: SampleRepository) {
    composable(Routes.Details) { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        val item = repo.findById(id)
        DetailsScreen(item = item, onBack = { navController.popBackStack() })
    }
}
