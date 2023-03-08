package tuver.movies.ui.movie.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import tuver.movies.R
import tuver.movies.databinding.FragmentMovieListBinding
import tuver.movies.provider.MovieImageProvider
import tuver.movies.ui.movie.movielist.adapter.MovieSummaryAdapter
import tuver.movies.util.extension.viewModelsFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    @Inject
    lateinit var movieImageProvider: MovieImageProvider

    @Inject
    lateinit var viewModelFactory: MovieListViewModel.Companion.Factory

    private val viewModel: MovieListViewModel by viewModelsFactory { viewModelFactory.create(resources.getInteger(R.integer.movie_list_page_size)) }

    private val movieSummaryAdapter by lazy { MovieSummaryAdapter(movieImageProvider) { viewModel.onMovieSummaryClick(it) } }

    private var bindingFiled: FragmentMovieListBinding? = null

    private val binding: FragmentMovieListBinding
        get() = bindingFiled ?: throw IllegalAccessException("Cannot access binding!")

    private fun setupRecyclerView() {
        binding.movieRecyclerView.apply {
            adapter = movieSummaryAdapter
            layoutManager = StaggeredGridLayoutManager(resources.getInteger(R.integer.movie_list_grid_span_count), VERTICAL)
        }
    }

    private fun onNavigationChanged(navigation: MovieListNavigation) {
        when (navigation) {
            is MovieListNavigation.NavigateToMovieDetail -> {
                val action = MovieListFragmentDirections.toMovieDetailFragment(
                    navigation.movieSummaryModel
                )

                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            bindingFiled = FragmentMovieListBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.navigation.observe(viewLifecycleOwner, this::onNavigationChanged)
        lifecycleScope.launch {
            viewModel.movieSummaryListPagingDataFlow.collectLatest { pagingData ->
                movieSummaryAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingFiled = null
    }

}