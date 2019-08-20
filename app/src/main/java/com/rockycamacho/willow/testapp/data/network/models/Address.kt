package com.rockycamacho.willow.testapp.data.network.models

import com.squareup.moshi.Json

data class Address(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "line1")
    val line1: String? = null,
    @field:Json(name = "line2")
    val line2: String? = null,
    @field:Json(name = "city")
    val city: String? = null,
    @field:Json(name = "state")
    val state: String? = null,
    @field:Json(name = "zipCode")
    val zipCode: String? = null,
    @field:Json(name = "country")
    val country: String? = null,
    @field:Json(name = "longitude")
    val longitude: Double? = null,
    @field:Json(name = "latitude")
    val latitude: Double? = null
)