package com.studiolaum.sunflowerclone.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "gardenPlant",
    indices = [Index(value = ["plantId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["id"],
            childColumns = ["plantId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class GardenPlant(
    val plantId: Long,
    val plantedTimeMilli: Long,
    val lastWateredTimeMilli: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)
