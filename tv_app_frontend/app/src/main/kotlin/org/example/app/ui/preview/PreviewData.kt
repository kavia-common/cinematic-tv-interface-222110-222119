package org.example.app.ui.preview

import org.example.app.data.MediaItem
import org.example.app.data.GenreRowData
import org.example.app.data.TabItem

// PUBLIC_INTERFACE
object PreviewData {
    val tabs = listOf(
        TabItem("home", "Home"),
        TabItem("search", "Search"),
        TabItem("settings", "Settings")
    )

    val featured = MediaItem(
        "f1",
        "Interstellar",
        "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
        "Sci-Fi",
        "https://image.tmdb.org/t/p/w780/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg"
    )

    val rows = listOf(
        GenreRowData("Sci-Fi", List(6) { i ->
            MediaItem("s$i", "Sci-Fi ${i + 1}", "Desc", "Sci-Fi", "https://image.tmdb.org/t/p/w342/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg")
        }),
        GenreRowData("Horror", List(6) { i ->
            MediaItem("h$i", "Horror ${i + 1}", "Desc", "Horror", "https://image.tmdb.org/t/p/w342/2S0h67yXYYoJ2zs7Gg2V2pAAgCk.jpg")
        })
    )
}
