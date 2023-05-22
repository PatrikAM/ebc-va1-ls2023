package cz.pef.mendelu.exam.di

import cz.pef.mendelu.exam.database.CountersDao
import cz.pef.mendelu.exam.database.CountersDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideTasksDao(database: CountersDatabase): CountersDao {
        return database.tasksDao()
    }

    single { provideTasksDao(get()) }

}