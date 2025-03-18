package com.studiolaum.sunflowerclone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.network.UnsplashApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class MyGardenViewModel : ViewModel() {
    private val _plantList = MutableLiveData<MutableList<Plant>>(mutableListOf())

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
                date.timeInMillis,
                ""
            )

        }
        viewModelScope.launch {
            initPhotoUrl()
        }
    }

    private suspend fun initPhotoUrl() {
        withContext(Dispatchers.IO) {
            val urls = List(_plantList.value?.size ?: 0) {
                getPhotoUrl()
            }

            _plantList.postValue(
                _plantList.value?.mapIndexed { index, plant ->
                    plant.copy(url = urls[index])
                }?.toMutableList()
            )
        }
    }


    private suspend fun getPhotoUrl(searchTerm: String = "apple"): String {
        var url = ""
        try {
            val photo = UnsplashApi.unsplashService.searchPhoto(searchTerm, 3).results.first()
            url = photo.urls.small
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return url
    }
}