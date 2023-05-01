package cz.mendelu.pef.va1.xmichl.meminiapp.di

import cz.mendelu.pef.va1.xmichl.meminiapp.MeminiApp
import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(): MemoriesDatabase {
        return MemoriesDatabase.getDatabase(MeminiApp.appContext)
    }

    single { provideDatabase() }

}
