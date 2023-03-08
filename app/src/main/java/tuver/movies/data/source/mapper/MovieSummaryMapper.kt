package tuver.movies.data.source.mapper

import tuver.movies.data.source.dto.MovieSummaryDto
import tuver.movies.model.MovieSummaryModel

interface MovieSummaryMapper {

    fun toModel(dto: MovieSummaryDto): MovieSummaryModel

}