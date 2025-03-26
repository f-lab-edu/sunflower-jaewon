package com.studiolaum.sunflowerclone.di

import android.content.Context
import androidx.room.Room
import com.studiolaum.sunflowerclone.data.AppDatabase
import com.studiolaum.sunflowerclone.data.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "plant_database"
        ).build()
    }

    @Provides
    fun providePlantDao(database: AppDatabase): PlantDao {
        return database.PlantDao()
    }
}