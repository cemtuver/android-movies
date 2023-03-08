package tuver.movies.ui.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tuver.movies.domain.GetMovieUseCase
import tuver.movies.model.MovieModel
import tuver.movies.model.MovieSummaryModel
import tuver.movies.util.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class MovieDetailViewModel @AssistedInject constructor(
    @Assisted private val movieSummaryModel: MovieSummaryModel,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val mutableMovieModel = MutableLiveData(
        movieSummaryModel.run {
            MovieModel(
                id = id,
                title = title,
                overview = overview,
                releaseDate = releaseDate,
                posterImageUrlPath = posterImageUrlPath,
                backdropImageUrlPath = null,
                runtimeInMinutes = 0
            )
        }
    )

    private val mutableNavigation = tuver.movies.util.SingleLiveEvent<MovieDetailNavigation>()

    val movieModel: LiveData<MovieModel>
        get() = mutableMovieModel

    val navigation: LiveData<MovieDetailNavigation>
        get() = mutableNavigation

    init {
        viewModelScope.launch {
            getMovieUseCase.getMovieUseCase(movieSummaryModel.id).onSuccess {
                mutableMovieModel.postValue(it)
            }
        }
    }

    fun onBackClick() {
        mutableNavigation.postValue(MovieDetailNavigation.NavigateUp)
    }

    companion object {

        @AssistedFactory
        interface Factory {
            fun create(movieSummaryModel: MovieSummaryModel): MovieDetailViewModel
        }

    }

}