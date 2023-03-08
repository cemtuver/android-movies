package tuver.movies.data.source

import tuver.movies.data.MovieRepository
import tuver.movies.data.error.DtoNotFetchedError
import tuver.movies.data.source.local.MovieDatabase
import tuver.movies.data.source.mapper.MovieMapper
import tuver.movies.data.source.remote.MovieApi
import tuver.movies.model.MovieModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CachedMovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase,
    private val movieMapper: MovieMapper,
) : MovieRepository {

    override suspend fun getMovie(movieId: Int): Result<MovieModel> {
        val movieDto = try {
            movieApi.getMovie(movieId).also { movieDatabase.movieDao().insert(it) }
        } catch (exception: Exception) {
            if (exception !is IOException && exception !is HttpException) throw exception
            movieDatabase.movieDao().select(movieId)
        }

        return when (movieDto) {
            null -> Result.failure(DtoNotFetchedError("Unable to fetch movie with id $movieId"))
            else -> Result.success(movieMapper.toModel(movieDto))
        }
    }

}
