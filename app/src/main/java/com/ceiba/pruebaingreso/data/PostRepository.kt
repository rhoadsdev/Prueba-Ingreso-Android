package com.ceiba.pruebaingreso.data

import com.ceiba.pruebaingreso.data.database.dao.PostDao
import com.ceiba.pruebaingreso.data.database.entities.toDatabase
import com.ceiba.pruebaingreso.data.model.PostResponse
import com.ceiba.pruebaingreso.data.network.PostService
import com.ceiba.pruebaingreso.domain.model.Post
import com.ceiba.pruebaingreso.domain.model.toDomain
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostService,
    private val postDao: PostDao
) {
    suspend fun getPosts(userId: Int): List<Post>? {
        var result = getPostsLocal(userId)
        if (result.isNullOrEmpty()) {
            val response = getPostsApi(userId)
            if (!response.isNullOrEmpty()) {
                postDao.insertAllPosts(response.map { it.toDatabase() })
                result = response.map { it.toDomain() }
            }
        }
        return result
    }

    suspend fun getPostsApi(userId: Int): List<PostResponse>? = api.getPosts(userId)

    suspend fun getPostsLocal(userId: Int): List<Post>? = postDao.getPostsByUser(userId)
}