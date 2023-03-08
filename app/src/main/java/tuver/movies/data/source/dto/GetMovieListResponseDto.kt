package tuver.movies.data.source.dto

import com.google.gson.annotations.SerializedName

data class GetMovieListResponseDto(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("results") val movieSummaryList: List<MovieSummaryDto>
)
