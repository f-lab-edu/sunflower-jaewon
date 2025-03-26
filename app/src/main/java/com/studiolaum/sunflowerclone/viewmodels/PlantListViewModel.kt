package com.studiolaum.sunflowerclone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.data.PlantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class PlantListViewModel(private val plantRepository: PlantRepository) : ViewModel() {
    private val _plantList = MutableLiveData<MutableList<Plant>>(mutableListOf())

    val plantList: LiveData<List<Plant>>
        get() = _plantList.map { it.toList() }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val plants = plantRepository.getPlantList()
            _plantList.postValue(plants.toMutableList())
        }
    }
}

class PlantListViewModelFactory(
    private val plantRepository: PlantRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantListViewModel(plantRepository) as T
    }
}