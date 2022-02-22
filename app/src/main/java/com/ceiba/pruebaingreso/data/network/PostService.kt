package com.ceiba.pruebaingreso.data.network

import com.ceiba.pruebaingreso.data.model.PostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostService @Inject constructor(
    private val api: PostApiClient
) {
    suspend fun getPosts(userId: Int): List<PostResponse>? {
        return withContext(Dispatchers.IO) {
            val response = api.getPosts(userId)
            response.body() ?: null
        }
    }
}