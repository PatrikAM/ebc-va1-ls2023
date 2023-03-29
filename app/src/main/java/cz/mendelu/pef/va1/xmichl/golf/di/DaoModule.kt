package cz.mendelu.pef.va1.xmichl.golf.di

import cz.mendelu.pef.va1.xmichl.golf.database.GolfistsDao
import cz.mendelu.pef.va1.xmichl.golf.database.GolfistsDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideTasksDao(database: GolfistsDatabase): GolfistsDao {
        return database.golfistsDao()
    }

    single { provideTasksDao(get()) }

}