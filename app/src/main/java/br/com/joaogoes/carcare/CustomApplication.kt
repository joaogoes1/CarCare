package br.com.joaogoes.carcare

import android.app.Application
import br.com.joaogoes.carcare.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApplication)
            modules(appModule)
        }
    }
}