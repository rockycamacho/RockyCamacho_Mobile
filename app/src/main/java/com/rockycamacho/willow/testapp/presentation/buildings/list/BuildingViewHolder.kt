package com.rockycamacho.willow.testapp.presentation.buildings.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.rockycamacho.willow.testapp.R
import com.rockycamacho.willow.testapp.data.network.models.AvailableProduct
import com.rockycamacho.willow.testapp.data.network.models.Building
import kotlinx.android.synthetic.main.item_building.view.*
import java.lang.StringBuilder

class BuildingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Building, viewOnMapListener: (Building) -> Unit, registerListener: (Building) -> Unit, goToExplorerListener: (Building) -> Unit) {
        var stateCountry = buildStateCountry(item)
        var addressLine = buildAddressLine(item)
        itemView.state_city.text = stateCountry.toString()
        itemView.address.text = addressLine.toString()
        itemView.image.load(url = item.imageUrl) {
            crossfade(true)
            placeholder(R.color.placeholder_image)
            transformations(RoundedCornersTransformation(10f))
        }
        itemView.view_on_map_button.setOnClickListener {
            viewOnMapListener(item)
        }
        itemView.register_button.setOnClickListener {
            item.isRegistered = !item.isRegistered
            registerListener(item)
        }
        itemView.list_item.setOnClickListener {
            goToExplorerListener(item)
        }
        itemView.register_button.visibility = when(item.availableProducts.contains(AvailableProduct.REGISTER)) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        itemView.check.visibility = when(item.isRegistered) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun buildStateCountry(item: Building): StringBuilder {
        var stateCountry = StringBuilder()
        item.address?.let {
            it.state?.let {
                stateCountry.append(it)
            }
            it.country?.let {
                if (stateCountry.length > 0) {
                    stateCountry.append(", ")
                }
                stateCountry.append(it)
            }
        }
        return stateCountry
    }

    private fun buildAddressLine(item: Building): StringBuilder {
        var addressLine = StringBuilder()
        item.address?.let {
            it.line1?.let {
                addressLine.append(it)
            }
            it.line2?.let {
                if (addressLine.length > 0) {
                    addressLine.append(", ")
                }
                addressLine.append(it)
            }
        }
        return addressLine
    }

}
