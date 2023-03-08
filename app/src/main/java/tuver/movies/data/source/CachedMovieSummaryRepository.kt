package tuver.movies.data.source

import androidx.paging.*
import tuver.movies.data.MovieSummaryRepository
import tuver.movies.data.source.local.MovieDatabase
import tuver.movies.data.source.mapper.MovieSummaryMapper
import tuver.movies.data.source.mediator.MovieSummaryRemoteMediator
import tuver.movies.model.MovieSummaryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CachedMovieSummaryRepository @Inject constructor(
    private val movieDatabase: MovieDatabase,
    private val movieSummaryMapper: MovieSummaryMapper,
    private val movieSummaryRemoteMediatorFactory: MovieSummaryRemoteMediator.Companion.Factory
) : MovieSummaryRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieSummaryListPagingData(pageSize: Int): Flow<PagingData<MovieSummaryModel>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = { movieDatabase.movieSummaryDao().selectPagingSource() },
            remoteMediator = movieSummaryRemoteMediatorFactory.create(pageSize)
        )
            .flow
            .map { pagingData -> pagingData.map(movieSummaryMapper::toModel) }
    }

}
