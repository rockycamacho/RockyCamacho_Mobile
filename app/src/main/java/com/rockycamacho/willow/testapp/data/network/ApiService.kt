package com.rockycamacho.willow.testapp.data.network

import com.rockycamacho.willow.testapp.data.network.models.Building
import com.rockycamacho.willow.testapp.data.network.models.User
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("858629f93f628c62c4e29a3bb1d99bff/raw/bc403f2f39fbd3d41e48ff893f24d0545907ece0/login")
    fun getUser(): Single<User>

    @GET("c5eb3b858ff810febd3dfbd5960d3fd8/raw/64a0ba3ee02d52536157d2dd01dddb1069175a8f/buildings")
    fun getExercises(): Single<List<Building>>

}
