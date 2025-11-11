package org.example.app.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.app.data.GenreRowData
import org.example.app.data.MediaItem
import org.example.app.data.TabItem
import org.example.app.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        val tabs = listOf(
            TabItem("home", "Home"),
            TabItem("search", "Search"),
            TabItem("settings", "Settings")
        )
        val featured = MediaItem(
            id = "interstellar",
            title = "Interstellar",
            description = "A team of explorers travel through a wormhole in space.",
            genre = "Sci-Fi",
            imageUrl = "https://image.tmdb.org/t/p/w780/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg"
        )
        val rows = listOf(
            GenreRowData("Sci-Fi", List(6) { i ->
                MediaItem("$i", "Sci-Fi ${i + 1}", "Desc", "Sci-Fi", "https://image.tmdb.org/t/p/w342/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg")
            })
        )
        HomeScreen(tabs = tabs, featured = featured, genreRows = rows, onCardClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun FeaturedBannerPreview() {
    AppTheme {
        val featured = MediaItem(
            id = "interstellar",
            title = "Interstellar",
            description = "A team of explorers travel through a wormhole in space.",
            genre = "Sci-Fi",
            imageUrl = "https://image.tmdb.org/t/p/w780/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg"
        )
        FeaturedBanner(item = featured, scrollOffset = 0)
    }
}
