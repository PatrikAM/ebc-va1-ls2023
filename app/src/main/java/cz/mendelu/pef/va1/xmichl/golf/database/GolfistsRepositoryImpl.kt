package cz.mendelu.pef.va1.xmichl.golf.database

import cz.mendelu.pef.va1.xmichl.golf.model.Golfist
import kotlinx.coroutines.flow.Flow

class GolfistsRepositoryImpl(private val dao: GolfistsDao)
    : IGolfistsRepository {

    override fun getAll(): Flow<List<Golfist>> {
        return dao.getAll()
    }

    override suspend fun insert(golfist: Golfist): Long {
        return dao.insert(golfist)
    }

}