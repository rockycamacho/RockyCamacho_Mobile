package com.rockycamacho.willow.testapp.data.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Building(
    @field:Json(name = "id")
    val id: Long? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "clientId")
    val clientId: Long? = null,
    @field:Json(name = "clientName")
    val clientName: String? = null,
    @field:Json(name = "address")
    val address: Address? = null,
    @field:Json(name = "availableProducts")
    val availableProducts: List<AvailableProduct> = listOf(),
    @field:Json(name = "imageUrl")
    val imageUrl: String? = null,
    @field:Json(name = "isRegistered")
    var isRegistered: Boolean = false
) : Parcelable