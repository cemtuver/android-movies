package tuver.movies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tuver.movies.data.source.dto.MovieDto
import tuver.movies.data.source.dto.MovieSummaryDto
import tuver.movies.data.source.dto.RemoteKeyDto
import tuver.movies.data.source.local.MovieDatabase.Companion.VERSION

@Database(entities = [MovieSummaryDto::class, MovieDto::class, RemoteKeyDto ::class], version = VERSION)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieSummaryDao(): MovieSummaryDao

    abstract fun remoteKeyDao(): RemoteKeyDao

    abstract fun movieDao(): MovieDao

    companion object {

        const val VERSION = 1
        const val NAME = "movie-db"

    }

}