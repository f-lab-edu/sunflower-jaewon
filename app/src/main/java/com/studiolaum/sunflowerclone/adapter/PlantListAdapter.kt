package com.studiolaum.sunflowerclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.databinding.MyGardenListItemBinding

class PlantListAdapter :
    ListAdapter<Plant, PlantListAdapter.PlantViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PlantViewHolder(
        MyGardenListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
    )


    override fun onBindViewHolder(viewHolder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        viewHolder.bind(plant)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }
    }

    class PlantViewHolder(private val binding: MyGardenListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            binding.plant = plant
        }
    }
}