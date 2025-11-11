package org.example.app.data

// PUBLIC_INTERFACE
class SampleRepository {

    // Provided image URLs (examples for each genre)
    private val sciFiUrls = listOf(
        "https://image.tmdb.org/t/p/w342/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg", // Interstellar
        "https://image.tmdb.org/t/p/w342/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", // Interstellar alt
        "https://image.tmdb.org/t/p/w342/vgpXmVaVyUL7GGiDeiK1mKEKzcX.jpg"
    )
    private val horrorUrls = listOf(
        "https://image.tmdb.org/t/p/w342/2S0h67yXYYoJ2zs7Gg2V2pAAgCk.jpg",
        "https://image.tmdb.org/t/p/w342/5Y5pz0NX7yKhG1bN0mAGH9h3ZzR.jpg",
        "https://image.tmdb.org/t/p/w342/pxD1ZVq6KYFAsktwVDqveufqdpB.jpg"
    )
    private val romanceUrls = listOf(
        "https://image.tmdb.org/t/p/w342/yF1eOkaYvwiORauRCPWznV9xVvi.jpg",
        "https://image.tmdb.org/t/p/w342/5hqbJSmtAimbaP3XcYshCixuUtk.jpg",
        "https://image.tmdb.org/t/p/w342/sC6cs0TJEvnfS8lo4333y0GxAL9.jpg"
    )
    private val kidsUrls = listOf(
        "https://image.tmdb.org/t/p/w342/2YvG0XvVHHVxqyvtoRyyhY2aToI.jpg",
        "https://image.tmdb.org/t/p/w342/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
        "https://image.tmdb.org/t/p/w342/1wkiB9QWt8lY2Yv0yoyWZMhUlCT.jpg"
    )
    private val thrillerUrls = listOf(
        "https://image.tmdb.org/t/p/w342/1QpO9wo7JWecZ4NiBuu625FiY1j.jpg",
        "https://image.tmdb.org/t/p/w342/tcNniniS4rfqrLH0oORikJfnIwY.jpg",
        "https://image.tmdb.org/t/p/w342/9gBVqNrybLwukxjSbZ0eF1XlQnD.jpg"
    )
    private val suspenseUrls = listOf(
        "https://image.tmdb.org/t/p/w342/2h00HrZs89SL3tXB4NbC5hQ3glA.jpg",
        "https://image.tmdb.org/t/p/w342/o0nZbdB7CRG3Ejj6mUeJ8m4zR6s.jpg",
        "https://image.tmdb.org/t/p/w342/1Wlwnhn5sXUIwlxpJgWszT622PD.jpg"
    )

    private fun buildItems(urls: List<String>, genre: String): List<MediaItem> =
        urls.mapIndexed { i, url ->
            MediaItem(
                id = "${genre.lowercase()}-$i",
                title = "$genre ${i + 1}",
                description = "A $genre favorite handpicked for you.",
                genre = genre,
                imageUrl = url
            )
        }

    private val featuredItem = MediaItem(
        id = "interstellar",
        title = "Interstellar",
        description = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
        genre = "Sci-Fi",
        imageUrl = "https://image.tmdb.org/t/p/w780/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg"
    )

    // PUBLIC_INTERFACE
    fun tabs(): List<TabItem> = listOf(
        TabItem("home", "Home"),
        TabItem("search", "Search"),
        TabItem("settings", "Settings")
    )

    // PUBLIC_INTERFACE
    fun featured(): MediaItem = featuredItem

    // PUBLIC_INTERFACE
    fun genreRows(): List<GenreRowData> = listOf(
        GenreRowData("Sci-Fi", buildItems(sciFiUrls, "Sci-Fi")),
        GenreRowData("Horror", buildItems(horrorUrls, "Horror")),
        GenreRowData("Romance", buildItems(romanceUrls, "Romance")),
        GenreRowData("Kids/Animation", buildItems(kidsUrls, "Kids/Animation")),
        GenreRowData("Thriller", buildItems(thrillerUrls, "Thriller")),
        GenreRowData("Suspense", buildItems(suspenseUrls, "Suspense")),
    )

    // PUBLIC_INTERFACE
    fun findById(id: String): MediaItem? =
        (genreRows().flatMap { it.items } + featuredItem).find { it.id == id }
}
