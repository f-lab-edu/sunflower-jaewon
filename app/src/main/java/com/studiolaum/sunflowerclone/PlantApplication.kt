package com.studiolaum.sunflowerclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlantApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}