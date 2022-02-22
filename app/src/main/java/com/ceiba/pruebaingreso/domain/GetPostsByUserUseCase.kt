package com.ceiba.pruebaingreso.domain

import com.ceiba.pruebaingreso.data.PostRepository
import com.ceiba.pruebaingreso.domain.model.Post
import javax.inject.Inject

class GetPostsByUserUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(userId: Int): List<Post>? = repository.getPosts(userId)
}