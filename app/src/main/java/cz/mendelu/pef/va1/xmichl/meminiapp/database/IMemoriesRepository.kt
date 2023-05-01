package cz.mendelu.pef.va1.xmichl.meminiapp.database

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import kotlinx.coroutines.flow.Flow

interface IMemoriesRepository {
    fun getAll(): Flow<List<Memory>>
    suspend fun insert(memory: Memory): Long
    suspend fun getMemoryById(id: Long): Memory
    suspend fun update(memory: Memory)
//    suspend fun getMemoriesByDate(date: Long): Flow<List<Memory>>
}
