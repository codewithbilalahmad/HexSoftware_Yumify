package com.muhammad.yumify

import android.app.Application
import com.muhammad.yumify.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YumifyApplication : Application(){
    companion object{
        lateinit var INSTANCE : YumifyApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidContext(this@YumifyApplication)
            androidLogger()
            modules(appModule)
        }
    }
}