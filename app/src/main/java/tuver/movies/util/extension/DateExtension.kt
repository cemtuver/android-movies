package tuver.movies.util.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
private val YEAR_FORMAT = SimpleDateFormat("yyyy")

fun Date?.toYearString(): String {
    return when (this) {
        null -> ""
        else -> YEAR_FORMAT.format(this)
    }
}
