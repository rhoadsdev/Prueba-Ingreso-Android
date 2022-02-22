package com.ceiba.pruebaingreso.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ceiba.pruebaingreso.data.model.AddressEntity
import com.ceiba.pruebaingreso.data.model.CompanyEntity
import com.ceiba.pruebaingreso.data.model.PostResponse
import com.ceiba.pruebaingreso.data.model.UserResponse

@Entity(tableName = "post_table")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String
)

fun PostResponse.toDatabase() = PostEntity(
    userId = userId,
    title = title,
    body = body
)