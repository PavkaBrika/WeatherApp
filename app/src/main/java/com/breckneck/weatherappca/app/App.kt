package com.breckneck.weatherappca.app

import android.app.Application
import com.breckneck.weatherappca.di.appModule
import com.breckneck.weatherappca.di.dataModule
import com.breckneck.weatherappca.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(domainModule, dataModule, appModule)
        }
    }
}