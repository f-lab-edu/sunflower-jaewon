package com.studiolaum.sunflowerclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.studiolaum.sunflowerclone.viewmodels.MyGardenViewModel
import com.studiolaum.sunflowerclone.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val myGardenViewModel: MyGardenViewModel by viewModels()

    private val plantListViewModel: PlantListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}