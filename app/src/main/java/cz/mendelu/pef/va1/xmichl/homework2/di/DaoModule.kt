package cz.mendelu.pef.va1.xmichl.homework2.di

import cz.mendelu.pef.va1.xmichl.homework2.database.ContactsDao
import cz.mendelu.pef.va1.xmichl.homework2.database.ContactsDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideTasksDao(database: ContactsDatabase): ContactsDao {
        return database.contactsDao()
    }

    single { provideTasksDao(get()) }

}
