package com.studiolaum.sunflowerclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiolaum.sunflowerclone.adapter.PlantListAdapter
import com.studiolaum.sunflowerclone.databinding.FragmentMyGargenBinding
import com.studiolaum.sunflowerclone.viewModels.MyGardenViewModel

class MyGardenFragment : Fragment() {
    private val viewModel: MyGardenViewModel = MyGardenViewModel()
    private val binding by lazy {
        FragmentMyGargenBinding.inflate(layoutInflater)
            .apply { lifecycleOwner = this@MyGardenFragment }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.viewModel = viewModel
        binding.gardenRecyclerView.adapter = PlantListAdapter()
        return binding.root
    }
}