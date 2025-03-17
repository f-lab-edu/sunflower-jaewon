package com.studiolaum.sunflowerclone

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.databinding.FragmentDetailBinding
import kotlin.math.abs

class DetailFragment : Fragment() {
    private val binding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
            .apply { lifecycleOwner = this@DetailFragment }
    }
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, PagerFragment())
                .commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)
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

            binding.floatingButton.setOnClickListener {
                Toast.makeText(requireContext(), "This is ${plant?.name}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }


    override fun onDetach() {
        super.onDetach()
        backPressedCallback.remove()
    }
}