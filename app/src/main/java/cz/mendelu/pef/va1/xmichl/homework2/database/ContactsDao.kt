package cz.mendelu.pef.va1.xmichl.homework2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<Contact>>

    @Insert
    suspend fun insert(contact: Contact): Long

}