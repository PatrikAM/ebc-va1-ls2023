package cz.mendelu.pef.va1.xmichl.homework2.database

import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import kotlinx.coroutines.flow.Flow

interface IContactsRepository {
    fun getAll(): Flow<List<Contact>>
    suspend fun insert(contact: Contact): Long
}