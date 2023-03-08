package tuver.movies.provider.impl

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import tuver.movies.provider.MovieImageProvider
import tuver.movies.util.extension.loadFromUrl
import javax.inject.Inject

class MovieImageProviderImpl @Inject constructor(
    private val imageBaseUrl: String,
    private val posterSize: String,
    private val backdropSize: String,
    @DrawableRes private val posterPlaceholder: Int,
    @DrawableRes private val backdropPlaceholder: Int,
) : MovieImageProvider {

    lateinit var context: Context

    override fun loadPoster(imageView: ImageView, path: String?) {
        imageView.loadFromUrl(path?.let { "$imageBaseUrl$posterSize$it" }, posterPlaceholder)
    }

    override fun loadBackdrop(imageView: ImageView, path: String?) {
        imageView.loadFromUrl(path?.let { "$imageBaseUrl$backdropSize$it" }, backdropPlaceholder)
    }

}