package com.rockycamacho.willow.testapp.data.network.models

import com.squareup.moshi.Json

enum class AvailableProduct {
    @Json(name = "AssetExplorer")
    EXPLORER,
    @Json(name = "AssetRegister")
    REGISTER
}
