package tuver.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class MovieSummaryModel(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: Date?,
    val posterImageUrlPath: String?
) : Parcelable