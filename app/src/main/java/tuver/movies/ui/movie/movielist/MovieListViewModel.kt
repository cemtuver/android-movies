package tuver.movies.ui.movie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import tuver.movies.domain.GetMovieSummaryListPagingDataUseCase
import tuver.movies.model.MovieSummaryModel
import tuver.movies.util.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MovieListViewModel @AssistedInject constructor(
    @Assisted pageSize: Int,
    getMovieSummaryListPagingDataUseCase: GetMovieSummaryListPagingDataUseCase
) : ViewModel() {

    private val mutableNavigation = tuver.movies.util.SingleLiveEvent<MovieListNavigation>()

    val navigation: LiveData<MovieListNavigation>
        get() = mutableNavigation

    val movieSummaryListPagingDataFlow by lazy {
        getMovieSummaryListPagingDataUseCase
            .getMovieSummaryListPagingData(pageSize)
            .cachedIn(viewModelScope)
    }

    fun onMovieSummaryClick(movieSummaryModel: MovieSummaryModel) {
        mutableNavigation.postValue(MovieListNavigation.NavigateToMovieDetail(movieSummaryModel))
    }

    companion object {

        @AssistedFactory
        interface Factory {

            fun create(pageSize: Int): MovieListViewModel

        }

    }

}