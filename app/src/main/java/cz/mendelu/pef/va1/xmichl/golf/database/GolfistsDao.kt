package cz.mendelu.pef.va1.xmichl.golf.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cz.mendelu.pef.va1.xmichl.golf.model.Golfist
import kotlinx.coroutines.flow.Flow

@Dao
interface GolfistsDao {

    @Query("SELECT * FROM golfists")
    fun getAll(): Flow<List<Golfist>>

    @Insert
    suspend fun insert(golfist: Golfist): Long

}