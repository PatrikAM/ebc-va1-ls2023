package cz.mendelu.pef.va1.xmichl.golf.di

import cz.mendelu.pef.va1.xmichl.golf.GolfApplication
import cz.mendelu.pef.va1.xmichl.golf.database.GolfistsDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(): GolfistsDatabase {
        return GolfistsDatabase.getDatabase(GolfApplication.appContext)
    }

    single { provideDatabase() }

}