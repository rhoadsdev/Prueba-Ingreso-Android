package com.ceiba.pruebaingreso.data.network

import com.ceiba.pruebaingreso.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {
    //@Headers("Content-Type:application/json")
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}