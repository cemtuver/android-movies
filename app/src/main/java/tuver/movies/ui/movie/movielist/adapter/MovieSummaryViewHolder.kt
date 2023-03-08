package tuver.movies.ui.movie.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tuver.movies.databinding.ItemMovieSummaryBinding
import tuver.movies.model.MovieSummaryModel
import tuver.movies.provider.MovieImageProvider
import tuver.movies.util.extension.toYearString

class MovieSummaryViewHolder(
    private val binding: ItemMovieSummaryBinding,
    private val movieImageProvider: MovieImageProvider,
    private val movieSummaryClickListener: (MovieSummaryModel) -> Unit
) : ViewHolder(binding.root) {

    fun bind(movieSummaryModel: MovieSummaryModel) {
        binding.apply {
            movieImageProvider.loadPoster(moviePosterImage, movieSummaryModel.posterImageUrlPath)
            movieTitleText.text = movieSummaryModel.title
            movieReleaseYearText.text = movieSummaryModel.releaseDate.toYearString()
            root.setOnClickListener { movieSummaryClickListener(movieSummaryModel) }
        }
    }

    companion object {

        fun newInstance(
            parent: ViewGroup,
            movieImageProvider: MovieImageProvider,
            movieSummaryClickListener: (MovieSummaryModel) -> Unit
        ): MovieSummaryViewHolder {
            val binding = ItemMovieSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return MovieSummaryViewHolder(binding, movieImageProvider, movieSummaryClickListener)
        }

    }

}