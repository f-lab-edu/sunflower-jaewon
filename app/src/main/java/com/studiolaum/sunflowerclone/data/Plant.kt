package com.studiolaum.sunflowerclone.data

data class Plant(
    val name: String,
    val wateringNeeds: Int,
    val description: String,
    val plantedTimeMilli: Long,
    val lastWateredTimeMilli: Long
)
