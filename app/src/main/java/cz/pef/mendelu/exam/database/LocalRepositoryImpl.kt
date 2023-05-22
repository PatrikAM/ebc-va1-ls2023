package cz.pef.mendelu.exam.database

import cz.pef.mendelu.exam.model.Counter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(private val dao: CountersDao)
    : ILocalRepository {
    override fun getAll(): Flow<List<Counter>> {
        return dao.getAll()
    }

    override suspend fun insert(counter: Counter): Long {
        return dao.insert(counter)
    }

    override suspend fun delete(counter: Counter) {
        dao.delete(counter)
    }

    override suspend fun changeCurrentValue(id: Long, currentValue: Int) {
        dao.changeCurrentValue(id, currentValue)
    }

    override suspend fun getCounterById(id: Long): Counter {
        return dao.getCounterById(id)
    }

}