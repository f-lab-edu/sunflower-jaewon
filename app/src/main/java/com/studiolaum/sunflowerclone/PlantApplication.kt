package com.studiolaum.sunflowerclone

import android.app.Application
import android.content.Context

class PlantApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}