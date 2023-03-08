package tuver.movies.data.source.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import tuver.movies.data.source.dto.MovieDto.Companion.DB_TABLE_NAME

@Entity(DB_TABLE_NAME)
data class MovieDto(
    @PrimaryKey
    @ColumnInfo(FIELD_NAME_ID)
    @SerializedName(FIELD_NAME_ID)
    val id: Int,

    @ColumnInfo(FIELD_NAME_TITLE)
    @SerializedName(FIELD_NAME_TITLE)
    val title: String,

    @ColumnInfo(FIELD_NAME_OVERVIEW)
    @SerializedName(FIELD_NAME_OVERVIEW)
    val overview: String,

    @ColumnInfo(FIELD_NAME_RELEASE_DATE)
    @SerializedName(FIELD_NAME_RELEASE_DATE)
    val releaseDate: String,

    @ColumnInfo(FIELD_NAME_POSTER_PATH)
    @SerializedName(FIELD_NAME_POSTER_PATH)
    val posterImageUrlPath: String?,

    @ColumnInfo(FIELD_NAME_BACKDROP_PATH)
    @SerializedName(FIELD_NAME_BACKDROP_PATH)
    val backdropImageUrlPath: String?,

    @ColumnInfo(FIELD_NAME_RUNTIME)
    @SerializedName(FIELD_NAME_RUNTIME)
    val runtimeInMinutes: Int
) {

    companion object {

        const val DB_TABLE_NAME = "movie"
        const val FIELD_NAME_ID = "id"
        const val FIELD_NAME_TITLE = "title"
        const val FIELD_NAME_OVERVIEW = "overview"
        const val FIELD_NAME_RELEASE_DATE = "release_date"
        const val FIELD_NAME_POSTER_PATH = "poster_path"
        const val FIELD_NAME_BACKDROP_PATH = "backdrop_path"
        const val FIELD_NAME_RUNTIME = "runtime"

    }

}