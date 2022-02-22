package com.ceiba.pruebaingreso.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.pruebaingreso.data.database.entities.UserEntity
import com.ceiba.pruebaingreso.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getAllUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserEntity>)

}