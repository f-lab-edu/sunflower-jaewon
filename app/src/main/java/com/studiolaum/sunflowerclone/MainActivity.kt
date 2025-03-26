package com.studiolaum.sunflowerclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.studiolaum.sunflowerclone.data.PlantRepository
import com.studiolaum.sunflowerclone.data.createDatabase
import com.studiolaum.sunflowerclone.network.UnsplashApi
import com.studiolaum.sunflowerclone.viewmodels.MyGardenViewModel
import com.studiolaum.sunflowerclone.viewmodels.MyGardenViewModelFactory
import com.studiolaum.sunflowerclone.viewmodels.PlantListViewModel
import com.studiolaum.sunflowerclone.viewmodels.PlantListViewModelFactory


class MainActivity : AppCompatActivity() {
    private val plantRepository = PlantRepository(
        UnsplashApi,
        createDatabase().PlantDao()
    )

    private val myGardenViewModel: MyGardenViewModel by viewModels {
        MyGardenViewModelFactory(plantRepository)
    }

    private val plantListViewModel: PlantListViewModel by viewModels {
        PlantListViewModelFactory(plantRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myGardenViewModel.gardenPlantList
        plantListViewModel.plantList
    }

}