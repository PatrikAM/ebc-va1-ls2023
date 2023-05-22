package cz.pef.mendelu.exam.di

import cz.pef.mendelu.exam.database.CountersDatabase
import cz.pef.mendelu.exam.xmichl.CountersApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(): CountersDatabase {
        return CountersDatabase.getDatabase(CountersApplication.appContext)
    }

    single { provideDatabase() }

}