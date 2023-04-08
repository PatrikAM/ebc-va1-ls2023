package cz.mendelu.pef.va1.xmichl.homework2.di

import cz.mendelu.pef.va1.xmichl.homework2.ContactApplication
import cz.mendelu.pef.va1.xmichl.homework2.database.ContactsDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(): ContactsDatabase {
        return ContactsDatabase.getDatabase(ContactApplication.appContext)
    }

    single { provideDatabase() }

}
