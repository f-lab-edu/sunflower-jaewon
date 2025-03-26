package com.studiolaum.sunflowerclone.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    fun getAllPlant(): List<Plant>

    @Query("SELECT * FROM gardenPlant")
    fun getAllGardenPlant(): List<GardenPlantWithPlantInfo>

    @Query("SELECT * FROM plant WHERE name LIKE :name")
    fun getPlantByName(name: String): Plant

    @Query("SELECT * FROM plant WHERE id LIKE :id")
    fun getPlantById(id: Long): Plant

    @Query("SELECT * FROM gardenPlant WHERE plantId LIKE :plantId")
    fun getGardenPlantByPlantId(plantId: Long): GardenPlantWithPlantInfo

    @Insert
    fun insertAllPlant(vararg plants: Plant)

    @Insert
    fun insertGardenPlant(gardenPlant: GardenPlant)
}