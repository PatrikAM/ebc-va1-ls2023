package cz.mendelu.pef.va1.xmichl.homework2

import android.app.Application
import android.content.Context
import cz.mendelu.pef.va1.xmichl.homework2.di.daoModule
import cz.mendelu.pef.va1.xmichl.homework2.di.databaseModule
import cz.mendelu.pef.va1.xmichl.homework2.di.repositoryModule
import cz.mendelu.pef.va1.xmichl.homework2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ContactApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@ContactApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    daoModule,
                    databaseModule
                )
            )
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}