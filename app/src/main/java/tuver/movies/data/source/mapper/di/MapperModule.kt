package tuver.movies.data.source.mapper.di

import tuver.movies.data.source.mapper.MovieMapper
import tuver.movies.data.source.mapper.MovieSummaryMapper
import tuver.movies.data.source.mapper.impl.MovieMapperImpl
import tuver.movies.data.source.mapper.impl.MovieSummaryMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindMovieSummaryMapper(movieSummaryMapperImpl: MovieSummaryMapperImpl): MovieSummaryMapper

    @Binds
    @Singleton
    abstract fun bindMovieMapper(movieMapperImpl: MovieMapperImpl): MovieMapper

    companion object {

        @Provides
        @Named("releaseDateFormat")
        fun provideDateFormat(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd", Locale.US)
        }

    }

}