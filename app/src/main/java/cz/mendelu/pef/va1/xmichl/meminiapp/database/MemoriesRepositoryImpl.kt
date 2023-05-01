package cz.mendelu.pef.va1.xmichl.meminiapp.database

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import kotlinx.coroutines.flow.Flow

class MemoriesRepositoryImpl(private val dao: MemoriesDao)
    : IMemoriesRepository {

    override fun getAll(): Flow<List<Memory>> {
        return dao.getAll()
    }

    override suspend fun insert(memory: Memory): Long {
        TODO("Not yet implemented")
    }

    override suspend fun getMemoryById(id: Long): Memory {
        TODO("Not yet implemented")
    }

    override suspend fun update(memory: Memory) {
        TODO("Not yet implemented")
    }
}