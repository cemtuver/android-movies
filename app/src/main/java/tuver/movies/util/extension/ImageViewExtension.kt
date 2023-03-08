package tuver.movies.util.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.dispose
import coil.load

fun ImageView.loadFromUrl(url: String?, @DrawableRes placeholder: Int? = null) {
    dispose()
    if (url.isNullOrEmpty()) return

    load(url) {
        placeholder?.let {
            placeholder(it)
            error(it)
        }
    }
}