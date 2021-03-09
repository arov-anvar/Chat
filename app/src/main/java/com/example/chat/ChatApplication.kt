package com.example.chat

import android.app.Application
import com.example.chat.di.firebaseModule
import com.example.chat.di.repositoryModule
import com.example.chat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ChatApplication)
            modules(viewModelModule, repositoryModule, firebaseModule)
        }
    }
}