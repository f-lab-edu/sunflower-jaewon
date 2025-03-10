package com.studiolaum.sunflowerclone.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.studiolaum.sunflowerclone.data.Plant
import java.util.Calendar

class PlantListViewModel : ViewModel() {
    private val _plantList = MutableLiveData<MutableList<Plant>>()

    val plantList: LiveData<List<Plant>>
        get() = _plantList.map { it.toList() }

    init {
        val date = Calendar.getInstance()
        _plantList.value = MutableList(10) {
            date.add(Calendar.DAY_OF_MONTH, 1)
            Plant(
                "apple$it",
                (25 + it) % 30,
                "this is apple$it",
                date.timeInMillis,
                date.timeInMillis
            )
        }
    }
}