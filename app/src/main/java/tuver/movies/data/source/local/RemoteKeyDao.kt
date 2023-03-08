package tuver.movies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tuver.movies.data.source.dto.RemoteKeyDto

@Dao
interface RemoteKeyDao {

    @Query("SELECT * FROM ${RemoteKeyDto.DB_TABLE_NAME} WHERE ${RemoteKeyDto.FIELD_NAME_ID}=:id ")
    suspend fun select(id: String): RemoteKeyDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(remoteKeyDto: RemoteKeyDto)

}