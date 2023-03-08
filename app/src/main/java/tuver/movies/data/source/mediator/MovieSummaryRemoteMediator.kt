package tuver.movies.data.source.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import tuver.movies.data.source.dto.MovieSummaryDto
import tuver.movies.data.source.dto.RemoteKeyDto
import tuver.movies.data.source.local.MovieDatabase
import tuver.movies.data.source.remote.MovieApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieSummaryRemoteMediator @AssistedInject constructor(
    @Assisted private val pageSize: Int,
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, MovieSummaryDto>() {

    private val movieSummaryDao = movieDatabase.movieSummaryDao()

    private val remoteKeyDao = movieDatabase.remoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieSummaryDto>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> INITIAL_PAGE
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKey = movieDatabase.withTransaction { remoteKeyDao.select(REMOTE_KEY_ID) }
                if (remoteKey?.nextPageKey == null) return MediatorResult.Success(endOfPaginationReached = true)

                remoteKey.nextPageKey
            }
        }

        return try {
            val getMoveListResponse = movieApi.getMovieList(page)
            val movieSummaryList = getMoveListResponse.movieSummaryList
            val endOfPaginationReached = getMoveListResponse.page == getMoveListResponse.totalPages

            movieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) movieSummaryDao.deleteAll()

                if (movieSummaryList.isNotEmpty()) {
                    movieSummaryList.forEachIndexed { index, movieSummaryDto ->
                        movieSummaryDto.order = (page * pageSize) + index
                    }

                    movieSummaryDao.insertAll(movieSummaryList)
                }

                remoteKeyDao.insert(RemoteKeyDto(REMOTE_KEY_ID, page.plus(1).takeIf { !endOfPaginationReached }))
            }

            MediatorResult.Success(endOfPaginationReached)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    companion object {

        private const val INITIAL_PAGE = 1
        private const val REMOTE_KEY_ID = "movie_summary"

        @AssistedFactory
        interface Factory {

            fun create(pageSize: Int): MovieSummaryRemoteMediator

        }

    }

}