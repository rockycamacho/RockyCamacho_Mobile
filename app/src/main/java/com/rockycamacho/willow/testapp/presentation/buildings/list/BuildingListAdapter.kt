package com.rockycamacho.willow.testapp.presentation.buildings.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rockycamacho.willow.testapp.R
import com.rockycamacho.willow.testapp.data.network.models.Building

class BuildingListAdapter(
    private val viewOnMapListener: (Building) -> Unit,
    private val registerListener: (Building) -> Unit,
    private val goToExplorerListener: (Building) -> Unit
): ListAdapter<Building, BuildingViewHolder>(BuildingItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BuildingViewHolder(inflater.inflate(R.layout.item_building, parent, false))
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        holder.bind(getItem(position), viewOnMapListener, registerListener, goToExplorerListener)
    }
}