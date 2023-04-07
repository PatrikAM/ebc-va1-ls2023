package cz.mendelu.pef.va1.xmichl.homework2.database

import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(private val dao: ContactsDao)
    : IContactsRepository {

    override fun getAll(): Flow<List<Contact>> {
        return dao.getAll()
    }

    override suspend fun insert(contact: Contact): Long {
        return dao.insert(contact)
    }

}