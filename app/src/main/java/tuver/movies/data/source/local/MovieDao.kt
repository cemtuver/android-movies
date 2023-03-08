package tuver.movies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tuver.movies.data.source.dto.MovieDto

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDto: MovieDto)

    @Query("SELECT * FROM ${MovieDto.DB_TABLE_NAME} where ${MovieDto.FIELD_NAME_ID}=:id")
    suspend fun select(id: Int): MovieDto?

}