package com.studiolaum.sunflowerclone.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studiolaum.sunflowerclone.R
import com.studiolaum.sunflowerclone.data.Plant
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@BindingAdapter("plantList")
fun bindGardenRecyclerView(
    view: RecyclerView,
    data: List<Plant>,
) {
    val adapter = view.adapter as PlantListAdapter
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
