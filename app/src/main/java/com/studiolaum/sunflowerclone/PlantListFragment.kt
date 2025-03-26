package com.studiolaum.sunflowerclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.studiolaum.sunflowerclone.adapter.PlantListRecyclerAdapter
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.databinding.FragmentPlantListBinding
import com.studiolaum.sunflowerclone.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {
    private val viewModel: PlantListViewModel by activityViewModels()
    private val binding by lazy {
        FragmentPlantListBinding.inflate(layoutInflater)
            .apply { lifecycleOwner = this@PlantListFragment }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.viewModel = viewModel
        val adapter = PlantListRecyclerAdapter()
        adapter.setOnItemClickListener(object : PlantListRecyclerAdapter.OnItemClickEventListener {
            override fun onItemClick(view: View, plant: Plant?) {
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.setFragmentResult("plantInfo", bundleOf("plant" to plant))
                fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_view, DetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        })
        binding.plantListRecyclerView.adapter = adapter
        return binding.root
    }

}