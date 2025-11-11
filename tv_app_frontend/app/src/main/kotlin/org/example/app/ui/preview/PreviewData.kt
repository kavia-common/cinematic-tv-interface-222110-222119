package org.example.app.ui.preview

import org.example.app.data.MediaItem
import org.example.app.data.GenreRowData
import org.example.app.data.TabItem

// PUBLIC_INTERFACE
object PreviewData {
    val tabs = listOf(
        TabItem("home", "Home"),
        TabItem("movies", "Movies"),
        TabItem("series", "Series")
    )

    val featured = MediaItem("f1", "Preview Feature", "A featured preview item.", "Action")

    val rows = listOf(
        GenreRowData("Action", List(6) { i -> MediaItem("a$i", "Action $i", "Desc", "Action") }),
        GenreRowData("Comedy", List(6) { i -> MediaItem("c$i", "Comedy $i", "Desc", "Comedy") })
    )
}
