package cz.mendelu.pef.va1.xmichl.meminiapp.di

import cz.mendelu.pef.va1.xmichl.meminiapp.database.IMemoriesRepository
import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesDao
import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideTasksRepository(dao: MemoriesDao): IMemoriesRepository {
        return MemoriesRepositoryImpl(dao)
    }

    single { provideTasksRepository(get()) }


}
