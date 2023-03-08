package tuver.movies.data.source.local.di

import android.content.Context
import androidx.room.Room
import tuver.movies.data.source.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext applicationContext: Context): MovieDatabase {
        val databaseBuilder = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            MovieDatabase.NAME
        )

        return databaseBuilder.build()
    }

}