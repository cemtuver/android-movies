package tuver.movies.data.source.remote.di

import android.content.Context
import tuver.movies.BuildConfig
import tuver.movies.data.source.remote.MovieApi
import tuver.movies.data.source.remote.MovieApiConfigInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    fun provideMovieApiConfigInterceptor(@ApplicationContext context: Context): MovieApiConfigInterceptor {
        val currentLocale = context.resources.configuration.locale

        return MovieApiConfigInterceptor(
            BuildConfig.MOVIE_API_KEY,
            currentLocale.toLanguageTag()
        )
    }

    @Provides
    fun provideOkHttpClient(movieApiConfigInterceptor: MovieApiConfigInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(movieApiConfigInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.MOVIE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}