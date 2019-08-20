package com.rockycamacho.willow.testapp.data.network.models

import com.squareup.moshi.Json

data class User(

    @field:Json(name = "id")
    val id: Long? = null,
    @field:Json(name = "usernameName")
    val usernameName: String? = null

)