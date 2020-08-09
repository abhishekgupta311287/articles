package com.abhishekgupta.articles

import android.app.Application
import com.abhishekgupta.articles.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArticlesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ArticlesApplication)
            modules(appModule)
        }
    }
}