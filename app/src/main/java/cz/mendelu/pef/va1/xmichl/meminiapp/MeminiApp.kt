package cz.mendelu.pef.va1.xmichl.meminiapp

import android.app.Application
import android.content.Context
import cz.mendelu.pef.va1.xmichl.meminiapp.di.daoModule
import cz.mendelu.pef.va1.xmichl.meminiapp.di.databaseModule
import cz.mendelu.pef.va1.xmichl.meminiapp.di.repositoryModule
import cz.mendelu.pef.va1.xmichl.meminiapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MeminiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@MeminiApp)
            modules(listOf(
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
