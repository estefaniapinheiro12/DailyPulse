package com.estefaniapinheiro.dailypulse.android

import android.app.Application
import com.estefaniapinheiro.dailypulse.android.di.databaseModule
import com.estefaniapinheiro.dailypulse.android.di.viewModelsModule
import com.estefaniapinheiro.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule + databaseModule
        startKoin {

            androidContext(this@DailyPulseApp)
            modules(modules)

        }
    }
}