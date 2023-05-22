package cz.pef.mendelu.exam.xmichl

import android.app.Application
import android.content.Context
import cz.pef.mendelu.exam.di.daoModule
import cz.pef.mendelu.exam.di.databaseModule
import cz.pef.mendelu.exam.di.repositoryModule
import cz.pef.mendelu.exam.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CountersApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@CountersApplication)
            modules(listOf(
                // todo moduly
                viewModelModule,
                repositoryModule,
                daoModule,
                databaseModule
            ))

        }

    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
