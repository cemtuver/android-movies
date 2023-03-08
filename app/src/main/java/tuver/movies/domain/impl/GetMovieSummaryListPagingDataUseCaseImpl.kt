package tuver.movies.domain.impl

import androidx.paging.PagingData
import tuver.movies.data.MovieSummaryRepository
import tuver.movies.domain.GetMovieSummaryListPagingDataUseCase
import tuver.movies.model.MovieSummaryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieSummaryListPagingDataUseCaseImpl @Inject constructor(
    private val movieSummaryRepository: MovieSummaryRepository
) : GetMovieSummaryListPagingDataUseCase {

    override fun getMovieSummaryListPagingData(pageSize: Int): Flow<PagingData<MovieSummaryModel>> {
        return movieSummaryRepository.getMovieSummaryListPagingData(pageSize)
    }

}