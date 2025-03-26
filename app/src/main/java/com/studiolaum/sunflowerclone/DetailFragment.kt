package com.studiolaum.sunflowerclone

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.databinding.FragmentDetailBinding
import com.studiolaum.sunflowerclone.viewmodels.MyGardenViewModel
import kotlin.math.abs

class DetailFragment : Fragment() {
    private val myGardenViewModel: MyGardenViewModel by activityViewModels()
    private val binding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
            .apply { lifecycleOwner = this@DetailFragment }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.appbarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val threshold = (appBarLayout.totalScrollRange * 0.9).toInt()
            val isCollapsed = abs(verticalOffset) >= threshold
            binding.appbarText.visibility = if (isCollapsed) View.VISIBLE else View.INVISIBLE
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "plantInfo",
            this
        ) { _, bundle ->
            val plant = bundle.getParcelable<Plant>("plant")
            binding.plant = plant
            plant?.let {
                val isPlantInGarden = myGardenViewModel.isGardenPlant(plant.id)
                if (isPlantInGarden) {
                    binding.floatingButton.visibility = View.GONE
                } else {
                    setFloatingButtonClickListener(plant)
                }
            }
        }
        return binding.root
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setFloatingButtonClickListener(plant: Plant) {
        binding.floatingButton.setOnClickListener {
            myGardenViewModel.insertGardenPlant(plant.id) {
                showToast(getString(R.string.float_button_msg_format))
                binding.floatingButton.visibility = View.GONE
            }
        }
    }
}