package cz.pef.mendelu.exam.database

import androidx.room.*
import cz.pef.mendelu.exam.model.Counter
import kotlinx.coroutines.flow.Flow

@Dao
interface CountersDao {

    @Query("SELECT * FROM counters")
    fun getAll(): Flow<List<Counter>>

    @Insert
    suspend fun insert(counter: Counter): Long

    @Delete
    suspend fun delete(counter: Counter)

    @Query("UPDATE counters SET current_value = :currentValue WHERE id = :id")
    suspend fun changeCurrentValue(id: Long, currentValue: Int)

    @Query("SELECT * FROM counters WHERE id = :id")
    suspend fun getCounterById(id: Long): Counter

    @Update
    suspend fun update(task: Counter)

}