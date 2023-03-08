package tuver.movies.ui.movie.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import tuver.movies.R
import tuver.movies.databinding.FragmentMovieDetailBinding
import tuver.movies.model.MovieModel
import tuver.movies.provider.MovieImageProvider
import tuver.movies.util.extension.viewModelsFactory
import tuver.movies.util.extension.toYearString
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    @Inject
    lateinit var movieImageProvider: MovieImageProvider

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModel.Companion.Factory

    private val fragmentArgs: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieDetailViewModel by viewModelsFactory { viewModelFactory.create(fragmentArgs.movieSummaryModel) }

    private var bindingFiled: FragmentMovieDetailBinding? = null

    private val binding: FragmentMovieDetailBinding
        get() = bindingFiled ?: throw IllegalAccessException("Cannot access binding!")

    private fun onNavigationChanged(navigation: MovieDetailNavigation) {
        when (navigation) {
            is MovieDetailNavigation.NavigateUp -> findNavController().navigateUp()
        }
    }

    private fun onMovieModelChanged(movieModel: MovieModel) {
        binding.apply {
            movieImageProvider.loadBackdrop(movieBackdropImage, movieModel.backdropImageUrlPath)
            movieImageProvider.loadPoster(moviePosterImage, movieModel.posterImageUrlPath)
            movieInfoText.text = getString(R.string.movie_info_format, movieModel.releaseDate.toYearString(), movieModel.runtimeInMinutes)
            movieTitleText.text = movieModel.title
            movieOverviewText.text = movieModel.overview
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            bindingFiled = FragmentMovieDetailBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener { viewModel.onBackClick() }
        viewModel.navigation.observe(viewLifecycleOwner, this::onNavigationChanged)
        viewModel.movieModel.observe(viewLifecycleOwner, this::onMovieModelChanged)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingFiled = null
    }

}