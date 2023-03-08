package tuver.movies.ui.movie.movielist

import tuver.movies.model.MovieSummaryModel

abstract class MovieListNavigation {
    data class NavigateToMovieDetail(val movieSummaryModel: MovieSummaryModel) : MovieListNavigation()
}