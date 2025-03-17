package com.studiolaum.sunflowerclone.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plant(
    val name: String,
    val wateringNeeds: Int,
    val description: String,
    val plantedTimeMilli: Long,
    val lastWateredTimeMilli: Long,
    val url: String,
) : Parcelable
