package tuver.movies.domain

import androidx.paging.PagingData
import tuver.movies.model.MovieSummaryModel
import kotlinx.coroutines.flow.Flow

interface GetMovieSummaryListPagingDataUseCase {

    fun getMovieSummaryListPagingData(pageSize: Int): Flow<PagingData<MovieSummaryModel>>

}