package com.studiolaum.sunflowerclone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(private val plantRepository: PlantRepository) :
    ViewModel() {
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
