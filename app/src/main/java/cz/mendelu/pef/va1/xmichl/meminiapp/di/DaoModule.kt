package cz.mendelu.pef.va1.xmichl.meminiapp.di

import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesDao
import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideMemoriesDao(database: MemoriesDatabase): MemoriesDao {
        return database.memoriesDao()
    }

    single { provideMemoriesDao(get()) }

}
