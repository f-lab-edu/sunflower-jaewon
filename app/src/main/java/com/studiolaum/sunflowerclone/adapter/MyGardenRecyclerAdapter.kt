package com.studiolaum.sunflowerclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studiolaum.sunflowerclone.data.GardenPlantWithPlantInfo
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.databinding.MyGardenListItemBinding

class MyGardenRecyclerAdapter :
    ListAdapter<GardenPlantWithPlantInfo, MyGardenRecyclerAdapter.PlantViewHolder>(DiffCallback) {

    interface OnItemClickEventListener {
        fun onItemClick(view: View, plant: Plant?)
    }

    private var itemClickListener: OnItemClickEventListener? = null

    fun setOnItemClickListener(itemClickListener: OnItemClickEventListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PlantViewHolder(
        MyGardenListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
    )


    override fun onBindViewHolder(viewHolder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        viewHolder.bind(plant)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GardenPlantWithPlantInfo>() {
        override fun areItemsTheSame(
            oldItem: GardenPlantWithPlantInfo,
            newItem: GardenPlantWithPlantInfo
        ): Boolean {
            return oldItem.gardenPlant.id == newItem.gardenPlant.id
        }

        override fun areContentsTheSame(
            oldItem: GardenPlantWithPlantInfo,
            newItem: GardenPlantWithPlantInfo
        ): Boolean {
            return oldItem.gardenPlant == newItem.gardenPlant
        }
    }

    inner class PlantViewHolder(private val binding: MyGardenListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemClickListener?.let { onItemClickListener ->
                binding.root.setOnClickListener {
                    onItemClickListener.onItemClick(it, binding.gardenPlant?.plant)
                }
            }
        }

        fun bind(gardenPlant: GardenPlantWithPlantInfo) {
            binding.gardenPlant = gardenPlant
        }
    }
}