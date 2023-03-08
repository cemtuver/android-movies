package tuver.movies.data.source.mapper.impl

import tuver.movies.data.source.dto.MovieSummaryDto
import tuver.movies.data.source.mapper.MovieSummaryMapper
import tuver.movies.model.MovieSummaryModel
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

class MovieSummaryMapperImpl @Inject constructor(
    @Named("releaseDateFormat") private val releaseDateFormat: SimpleDateFormat
) : MovieSummaryMapper {

    override fun toModel(dto: MovieSummaryDto): MovieSummaryModel {
        return dto.run {
            MovieSummaryModel(
                id,
                title,
                overview,
                releaseDateFormat.parse(releaseDate),
                posterImageUrlPath
            )
        }
    }

}