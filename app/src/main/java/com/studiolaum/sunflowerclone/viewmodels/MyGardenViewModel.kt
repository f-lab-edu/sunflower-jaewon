package com.studiolaum.sunflowerclone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.studiolaum.sunflowerclone.data.GardenPlantWithPlantInfo
import com.studiolaum.sunflowerclone.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyGardenViewModel @Inject constructor(private val plantRepository: PlantRepository) :
    ViewModel() {
    private val _gardenPlantList =
        MutableLiveData<MutableList<GardenPlantWithPlantInfo>>(mutableListOf())

    val gardenPlantList: LiveData<List<GardenPlantWithPlantInfo>>
        get() = _gardenPlantList.map { it.toList() }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val gardenPlants = plantRepository.getGardenPlantList()
            _gardenPlantList.postValue(gardenPlants.toMutableList())
        }
    }

    fun insertGardenPlant(plantId: Long, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.insertGardenPlant(plantId)
            val gardenPlants = plantRepository.getGardenPlantList()
            _gardenPlantList.postValue(gardenPlants.toMutableList())
            launch(Dispatchers.Main) { callback() }
        }
    }

    fun isGardenPlant(plantId: Long) =
        _gardenPlantList.value?.any { it.plant.id == plantId } ?: false
}