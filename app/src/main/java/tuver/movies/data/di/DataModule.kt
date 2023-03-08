package tuver.movies.data.di

import tuver.movies.data.MovieRepository
import tuver.movies.data.MovieSummaryRepository
import tuver.movies.data.source.CachedMovieRepository
import tuver.movies.data.source.CachedMovieSummaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindMovieSummaryRepository(cachedMovieSummaryRepository: CachedMovieSummaryRepository): MovieSummaryRepository


    @Binds
    @Singleton
    abstract fun bindMovieRepository(cachedMovieRepository: CachedMovieRepository): MovieRepository

}