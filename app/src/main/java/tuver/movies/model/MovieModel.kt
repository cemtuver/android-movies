package tuver.movies.model

import java.util.Date

data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: Date?,
    val posterImageUrlPath: String?,
    val backdropImageUrlPath: String?,
    val runtimeInMinutes: Int
)