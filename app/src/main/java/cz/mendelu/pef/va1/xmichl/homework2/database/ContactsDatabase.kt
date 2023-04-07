package cz.mendelu.pef.va1.xmichl.homework2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.mendelu.pef.va1.xmichl.golf.model.Golfist

@Database(entities = [Golfist::class], version = 1, exportSchema = true)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        private var INSTANCE: ContactsDatabase? = null
        fun getDatabase(context: Context): ContactsDatabase {
            if (INSTANCE == null) {
                synchronized(ContactsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ContactsDatabase::class.java, "contacts_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}