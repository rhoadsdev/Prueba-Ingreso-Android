package com.ceiba.pruebaingreso.domain

import com.ceiba.pruebaingreso.data.UserRepository
import com.ceiba.pruebaingreso.domain.model.User
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User>? = repository.getUsers()
}