package com.studiolaum.sunflowerclone.data

import com.studiolaum.sunflowerclone.network.UnsplashApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PlantRepository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val db: PlantDao
) {
    suspend fun getPlantList(): List<Plant> {
        var plants = db.getAllPlant()
        if (plants.isEmpty()) {
            insertAllPlant(
                "Apple",
                "Banana",
                "Cherry",
                "Mango",
                "Orange",
                "Peach",
                "Grape",
                "Pineapple",
                "Strawberry",
                "Watermelon"
            )
            plants = db.getAllPlant()
        }
        return plants
    }

    suspend fun getPlant(id: Long): Plant {
        return db.getPlantById(id)
    }

    suspend fun getGardenPlantList(): List<GardenPlantWithPlantInfo> {
        return db.getAllGardenPlant()
    }

    suspend fun getGardenPlant(plantId: Long): GardenPlantWithPlantInfo {
        return db.getGardenPlantByPlantId(plantId)
    }

    suspend fun insertGardenPlant(plantId: Long) {
        val time = System.currentTimeMillis()
        val gardenPlant = GardenPlant(plantId, time, time)
        db.insertGardenPlant(gardenPlant)
    }

    private suspend fun insertAllPlant(vararg names: String) {
        withContext(Dispatchers.IO) {
            val urls = Array(names.size) {
                getPhotoUrl(names[it])
            }
            val plants = Array(names.size) {
                Plant(names[it], 30 % (it + 1), "this is ${names[it]}", urls[it])
            }
            db.insertAllPlant(*plants)
        }
    }

    private suspend fun getPhotoUrl(searchTerm: String = "apple"): String {
        var url = ""
        try {
            val photo = unsplashApi.searchPhoto(searchTerm, 3).results.first()
            url = photo.urls.small
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return url
    }
}