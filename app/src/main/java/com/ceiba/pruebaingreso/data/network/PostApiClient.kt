package com.ceiba.pruebaingreso.data.network

import com.ceiba.pruebaingreso.data.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiClient {
    @GET("posts")
    suspend fun getPosts(@Query("userId") userId: Int): Response<List<PostResponse>>
}