package tuver.movies.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuver.movies.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }

}