package com.example.cleanarchitecturerandompics

import android.app.Application
import com.example.cleanarchitecturerandompics.di.networkModules
import com.example.cleanarchitecturerandompics.di.repositoryModules
import com.example.cleanarchitecturerandompics.di.useCaseModules
import com.example.cleanarchitecturerandompics.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModels +
                        repositoryModules +
                        useCaseModules +
                        networkModules
            )
        }
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }


}