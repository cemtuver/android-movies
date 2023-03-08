package tuver.movies.data.source.mapper

import tuver.movies.data.source.dto.MovieDto
import tuver.movies.model.MovieModel

interface MovieMapper {

    fun toModel(dto: MovieDto): MovieModel

}