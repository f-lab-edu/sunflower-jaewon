package com.studiolaum.sunflowerclone.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Plant::class, GardenPlant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PlantDao(): PlantDao
}
