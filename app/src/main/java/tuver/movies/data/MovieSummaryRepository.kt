package tuver.movies.data

import androidx.paging.PagingData
import tuver.movies.model.MovieSummaryModel
import kotlinx.coroutines.flow.Flow

interface MovieSummaryRepository {

    fun getMovieSummaryListPagingData(pageSize: Int): Flow<PagingData<MovieSummaryModel>>

}