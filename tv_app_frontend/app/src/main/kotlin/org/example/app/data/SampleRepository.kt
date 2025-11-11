package org.example.app.data

// PUBLIC_INTERFACE
class SampleRepository {
    private val items = listOf(
        MediaItem("1", "Edge of Tomorrow", "A soldier relives the same day, fighting aliens.", "Action"),
        MediaItem("2", "Mystic Falls", "A journey into a strange town with secrets.", "Drama"),
        MediaItem("3", "Deep Space", "Explorers find new life among the stars.", "Sci-Fi"),
        MediaItem("4", "Laugh Track", "A group of friends discover hilarity in daily life.", "Comedy"),
        MediaItem("5", "Hidden Truths", "Detectives unravel a conspiratorial web.", "Thriller"),
        MediaItem("6", "Ocean Echoes", "A family adventure across the seas.", "Family"),
        MediaItem("7", "Neon Nights", "A neon-soaked story of redemption.", "Action"),
        MediaItem("8", "Byte Me", "Hackers race against time.", "Sci-Fi"),
        MediaItem("9", "Starlit Path", "Two souls under a cosmic sky.", "Romance"),
        MediaItem("10", "Punchline", "Stand-up dreams in a small town.", "Comedy")
    )

    private val genres = listOf("Action", "Drama", "Sci-Fi", "Comedy", "Thriller", "Family", "Romance")

    // PUBLIC_INTERFACE
    fun tabs(): List<TabItem> = listOf(
        TabItem("home", "Home"),
        TabItem("movies", "Movies"),
        TabItem("series", "Series"),
        TabItem("mylist", "My List")
    )

    // PUBLIC_INTERFACE
    fun featured(): MediaItem = items.first()

    // PUBLIC_INTERFACE
    fun genreRows(): List<GenreRowData> = genres.map { g ->
        GenreRowData(
            title = g,
            items = items.filter { it.genre == g }.ifEmpty { items.shuffled().take(10) }
        )
    }

    // PUBLIC_INTERFACE
    fun findById(id: String): MediaItem? = items.find { it.id == id }
}
