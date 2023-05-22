package cz.pef.mendelu.exam.di

import cz.pef.mendelu.exam.database.CountersDao
import cz.pef.mendelu.exam.database.ILocalRepository
import cz.pef.mendelu.exam.database.LocalRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideTasksRepository(dao: CountersDao): ILocalRepository {
        return LocalRepositoryImpl(dao)
    }

    single { provideTasksRepository(get()) }


}