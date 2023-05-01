package cz.mendelu.pef.va1.xmichl.meminiapp.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoriesDao {

    @Query("SELECT * FROM memories")
    fun getAll(): Flow<List<Memory>>

    @Insert
    suspend fun insert(memory: Memory): Long

    @Query("SELECT * FROM memories WHERE id = :id")
    suspend fun getMemoryById(id: Long): Memory

    @Update
    suspend fun update(memory: Memory)

    @Query("SELECT * FROM memories WHERE date = :date")
    suspend fun getMemoriesByDate(date: Long): Flow<List<Memory>>

}
