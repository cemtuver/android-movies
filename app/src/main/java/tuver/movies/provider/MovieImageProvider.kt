package tuver.movies.provider

import android.widget.ImageView

interface MovieImageProvider {

    fun loadPoster(imageView: ImageView, path: String?)

    fun loadBackdrop(imageView: ImageView, path: String?)

}