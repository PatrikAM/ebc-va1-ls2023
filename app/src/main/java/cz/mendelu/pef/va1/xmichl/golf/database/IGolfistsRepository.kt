package cz.mendelu.pef.va1.xmichl.golf.database

import cz.mendelu.pef.va1.xmichl.golf.model.Golfist
import kotlinx.coroutines.flow.Flow

interface IGolfistsRepository {
    fun getAll(): Flow<List<Golfist>>
    suspend fun insert(golfist: Golfist): Long
}