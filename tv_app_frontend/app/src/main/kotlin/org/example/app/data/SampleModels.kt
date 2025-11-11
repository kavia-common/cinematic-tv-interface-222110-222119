package org.example.app.data

// PUBLIC_INTERFACE
data class MediaItem(
    val id: String,
    val title: String,
    val description: String,
    val genre: String,
    val imageUrl: String
)

// PUBLIC_INTERFACE
data class GenreRowData(
    val title: String,
    val items: List<MediaItem>
)

// PUBLIC_INTERFACE
data class TabItem(
    val id: String,
    val title: String
)
