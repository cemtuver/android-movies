package tuver.movies.domain.impl

import tuver.movies.data.MovieRepository
import tuver.movies.domain.GetMovieUseCase
import tuver.movies.model.MovieModel
import javax.inject.Inject

class GetMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieUseCase {

    override suspend fun getMovieUseCase(movieId: Int): Result<MovieModel> {
        return movieRepository.getMovie(movieId)
    }

}