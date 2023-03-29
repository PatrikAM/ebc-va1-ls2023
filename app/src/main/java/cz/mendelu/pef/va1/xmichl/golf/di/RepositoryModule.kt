package cz.mendelu.pef.va1.xmichl.golf.di

import cz.mendelu.pef.va1.xmichl.golf.database.GolfistsDao
import cz.mendelu.pef.va1.xmichl.golf.database.GolfistsRepositoryImpl
import cz.mendelu.pef.va1.xmichl.golf.database.IGolfistsRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideGolfistsRepository(dao: GolfistsDao): IGolfistsRepository {
        return GolfistsRepositoryImpl(dao)
    }

    single { provideGolfistsRepository(get()) }


}