package com.studiolaum.sunflowerclone.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studiolaum.sunflowerclone.R
import com.studiolaum.sunflowerclone.data.GardenPlantWithPlantInfo
import com.studiolaum.sunflowerclone.data.Plant
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@BindingAdapter("gardenPlants")
fun bindGardenRecyclerView(
    view: RecyclerView,
    data: List<GardenPlantWithPlantInfo>,
) {
    val adapter = view.adapter as MyGardenRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("plantList")
fun bindPlantListRecyclerView(
    view: RecyclerView,
    data: List<Plant>,
) {
    val adapter = view.adapter as PlantListRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("dateTimeMilli")
fun bindDateTimeMilli(
    view: TextView,
    data: Long,
) {
    val date = Calendar.getInstance().apply {
        timeInMillis = data
    }
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
    view.text = dateFormat.format(date.time)
}

@BindingAdapter("wateringNeeds")
fun bindWateringNeedDateText(
    view: TextView,
    data: Int,
) {
    view.text = view.context.getString(R.string.watering_need_format, data)
}

@BindingAdapter("detailWaterNeeds")
fun bindDetailWaterNeedsText(
    view: TextView,
    data: Int,
) {
    view.text = view.context.getString(R.string.detail_watering_needs_format, data)
}

@BindingAdapter("imageUrl")
fun bindPlantImageWithUrl(
    view: ImageView,
    data: String?
) {
    if (!data.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(data)
            .into(view)
    }
}
