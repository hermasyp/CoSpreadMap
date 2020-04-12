package com.catnip.cospreadmap.base

import android.app.Application
import com.catnip.cospreadmap.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule,databaseModule, dataSource,viewModels, repositories))
        }
    }

}