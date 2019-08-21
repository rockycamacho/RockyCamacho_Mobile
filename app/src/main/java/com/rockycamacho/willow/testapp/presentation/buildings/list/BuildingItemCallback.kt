package com.rockycamacho.willow.testapp.presentation.buildings.list

import androidx.recyclerview.widget.DiffUtil
import com.rockycamacho.willow.testapp.data.network.models.Building

class BuildingItemCallback : DiffUtil.ItemCallback<Building>() {
    override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.address == newItem.address &&
                oldItem.clientId == newItem.clientId &&
                oldItem.clientName == newItem.clientName &&
                oldItem.availableProducts == newItem.availableProducts &&
                oldItem.imageUrl == newItem.imageUrl
    }

}
