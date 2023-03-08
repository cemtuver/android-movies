package tuver.movies.provider.di

import android.content.Context
import tuver.movies.BuildConfig
import tuver.movies.R
import tuver.movies.provider.MovieImageProvider
import tuver.movies.provider.impl.MovieImageProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Provides
    @Singleton
    fun provideMovieImageProvider(@ApplicationContext context: Context): MovieImageProvider {
        return MovieImageProviderImpl(
            BuildConfig.MOVIE_API_IMAGE_BASE_URL,
            context.resources.getString(R.string.movie_poster_size),
            context.resources.getString(R.string.movie_backdrop_size),
            R.drawable.img_poster_placeholder,
            R.drawable.img_backdrop_placeholder
        )
    }

}