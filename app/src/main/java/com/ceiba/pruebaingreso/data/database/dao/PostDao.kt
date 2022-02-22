package com.ceiba.pruebaingreso.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.pruebaingreso.data.database.entities.PostEntity
import com.ceiba.pruebaingreso.data.database.entities.UserEntity
import com.ceiba.pruebaingreso.domain.model.Post
import com.ceiba.pruebaingreso.domain.model.User

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table WHERE userId = :userId ORDER BY id ASC")
    suspend fun getPostsByUser(userId: Int): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(posts: List<PostEntity>)

}