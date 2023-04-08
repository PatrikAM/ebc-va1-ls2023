package cz.mendelu.pef.va1.xmichl.homework2.di

import cz.mendelu.pef.va1.xmichl.homework2.database.ContactRepositoryImpl
import cz.mendelu.pef.va1.xmichl.homework2.database.ContactsDao
import cz.mendelu.pef.va1.xmichl.homework2.database.IContactsRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideTasksRepository(dao: ContactsDao): IContactsRepository {
        return ContactRepositoryImpl(dao)
    }

    single { provideTasksRepository(get()) }


}
