package com.ceiba.pruebaingreso.domain.model

import com.ceiba.pruebaingreso.data.model.PostResponse

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun PostResponse.toDomain() = Post(id, userId, title, body)