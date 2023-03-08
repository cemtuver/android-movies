package tuver.movies.ui.movie.movielist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import tuver.movies.model.MovieSummaryModel
import tuver.movies.provider.MovieImageProvider

class MovieSummaryAdapter(
    private val movieImageProvider: MovieImageProvider,
    private val movieSummaryClickListener: (MovieSummaryModel) -> Unit
) : PagingDataAdapter<MovieSummaryModel, MovieSummaryViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSummaryViewHolder {
        return MovieSummaryViewHolder.newInstance(parent, movieImageProvider, movieSummaryClickListener)
    }

    override fun onBindViewHolder(holder: MovieSummaryViewHolder, position: Int) {
        getItem(position)?.let { item -> holder.bind(item) }
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<MovieSummaryModel>() {

            override fun areItemsTheSame(oldItem: MovieSummaryModel, newItem: MovieSummaryModel): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: MovieSummaryModel, newItem: MovieSummaryModel): Boolean = oldItem == newItem

        }

    }

}