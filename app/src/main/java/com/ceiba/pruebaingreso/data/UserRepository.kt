package com.ceiba.pruebaingreso.data

import com.ceiba.pruebaingreso.data.database.dao.UserDao
import com.ceiba.pruebaingreso.data.database.entities.toDatabase
import com.ceiba.pruebaingreso.data.model.UserResponse
import com.ceiba.pruebaingreso.data.network.UserService
import com.ceiba.pruebaingreso.domain.model.User
import com.ceiba.pruebaingreso.domain.model.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService,
    private val userDao: UserDao
) {

    suspend fun getUsers(): List<User>? {
        var result = getUsersLocal()
        if (result.isNullOrEmpty()) {
            val response = getUsersApi()
            if (!response.isNullOrEmpty()) {
                userDao.insertAllUsers(response.map { it.toDatabase() })
                result = response.map { it.toDomain() }
            }
        }
        return result
    }

    suspend fun getUsersApi(): List<UserResponse>? = api.getUsers()

    suspend fun getUsersLocal(): List<User>? = userDao.getAllUsers()
}