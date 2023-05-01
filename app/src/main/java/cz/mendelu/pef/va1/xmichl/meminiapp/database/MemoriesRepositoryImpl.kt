package cz.mendelu.pef.va1.xmichl.meminiapp.database

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import kotlinx.coroutines.flow.Flow

class MemoriesRepositoryImpl(private val dao: MemoriesDao)
    : IMemoriesRepository {

    override fun getAll(): Flow<List<Memory>> {
        return dao.getAll()
    }

    override suspend fun insert(memory: Memory): Long {
        return dao.insert(memory)
    }

    override suspend fun getMemoryById(id: Long): Memory {
        return dao.getMemoryById(id)
    }

    override suspend fun update(memory: Memory) {
        dao.update(memory)
    }

//    override suspend fun getMemoriesByDate(date: Long): Flow<List<Memory>> {
//        return dao.getMemoriesByDate(date)
//    }

}