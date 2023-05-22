package cz.pef.mendelu.exam.database

import cz.pef.mendelu.exam.model.Counter
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    fun getAll(): Flow<List<Counter>>
    suspend fun insert(counter: Counter): Long
    suspend fun delete(counter: Counter)
    suspend fun changeCurrentValue(id: Long, currentValue: Int)
    suspend fun getCounterById(id: Long): Counter

}