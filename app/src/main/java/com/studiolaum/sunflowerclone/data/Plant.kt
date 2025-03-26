package com.studiolaum.sunflowerclone.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "plant", indices = [Index(value = ["name"], unique = true)])
data class Plant(
    val name: String,
    val wateringNeeds: Int,
    val description: String,
    val url: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
) : Parcelable
