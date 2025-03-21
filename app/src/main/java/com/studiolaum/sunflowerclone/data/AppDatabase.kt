package com.studiolaum.sunflowerclone.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studiolaum.sunflowerclone.PlantApplication

@Database(entities = [Plant::class, GardenPlant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PlantDao(): PlantDao
}

private lateinit var INSTANCE: AppDatabase

fun createDatabase(): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                PlantApplication.appContext,
                AppDatabase::class.java,
                "plant_database"
            ).build()
        }
    }
    return INSTANCE
}
