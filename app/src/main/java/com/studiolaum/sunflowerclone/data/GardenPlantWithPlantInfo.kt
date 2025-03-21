package com.studiolaum.sunflowerclone.data

import androidx.room.Embedded
import androidx.room.Relation

data class GardenPlantWithPlantInfo(
    @Embedded val gardenPlant: GardenPlant,
    @Relation(parentColumn = "plantId", entityColumn = "id")
    val plant: Plant,
)
