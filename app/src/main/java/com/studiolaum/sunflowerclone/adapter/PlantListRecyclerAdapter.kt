package com.studiolaum.sunflowerclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studiolaum.sunflowerclone.data.Plant
import com.studiolaum.sunflowerclone.databinding.PlantListItemBinding

class PlantListRecyclerAdapter :
    ListAdapter<Plant, PlantListRecyclerAdapter.PlantViewHolder>(DiffCallback) {

    interface OnItemClickEventListener {
        fun onItemClick(view: View, plant: Plant?)
    }

    private var itemClickListener: OnItemClickEventListener? = null

    fun setOnItemClickListener(itemClickListener: OnItemClickEventListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PlantViewHolder(
        PlantListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
    )


    override fun onBindViewHolder(viewHolder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        viewHolder.bind(plant)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }
    }

    inner class PlantViewHolder(private val binding: PlantListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemClickListener?.let { onItemClickListener ->
                binding.root.setOnClickListener {
                    onItemClickListener.onItemClick(it, binding.plant)
                }
            }
        }

        fun bind(plant: Plant) {
            binding.plant = plant
        }
    }
}