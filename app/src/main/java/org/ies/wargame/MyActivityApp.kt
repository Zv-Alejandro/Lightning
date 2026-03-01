package org.ies.wargame

import android.app.Application
import org.ies.wargame.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyActivityApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyActivityApp)
            modules(appModule)
        }
    }
}