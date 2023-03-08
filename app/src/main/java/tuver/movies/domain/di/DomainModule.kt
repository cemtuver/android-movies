package tuver.movies.domain.di

import tuver.movies.domain.GetMovieSummaryListPagingDataUseCase
import tuver.movies.domain.GetMovieUseCase
import tuver.movies.domain.impl.GetMovieSummaryListPagingDataUseCaseImpl
import tuver.movies.domain.impl.GetMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindGetMovieSummaryListPagingDataUseCase(
        getMovieSummaryListPagingDataUseCaseImpl: GetMovieSummaryListPagingDataUseCaseImpl
    ): GetMovieSummaryListPagingDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetMovieUseCase(getMovieUseCaseImpl: GetMovieUseCaseImpl): GetMovieUseCase

}