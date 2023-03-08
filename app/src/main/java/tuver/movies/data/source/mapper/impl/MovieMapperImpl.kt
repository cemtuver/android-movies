package tuver.movies.data.source.mapper.impl

import tuver.movies.data.source.dto.MovieDto
import tuver.movies.data.source.mapper.MovieMapper
import tuver.movies.model.MovieModel
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

class MovieMapperImpl @Inject constructor(
    @Named("releaseDateFormat") private val releaseDateFormat: SimpleDateFormat
) : MovieMapper {

    override fun toModel(dto: MovieDto): MovieModel {
        return dto.run {
            MovieModel(
                id,
                title,
                overview,
                releaseDateFormat.parse(releaseDate),
                posterImageUrlPath,
                backdropImageUrlPath,
                runtimeInMinutes
            )
        }
    }

}