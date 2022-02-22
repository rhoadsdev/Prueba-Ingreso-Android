package com.ceiba.pruebaingreso.domain.model

import com.ceiba.pruebaingreso.data.model.AddressEntity
import com.ceiba.pruebaingreso.data.model.CompanyEntity
import com.ceiba.pruebaingreso.data.model.UserResponse

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressEntity,
    val phone: String,
    val website: String,
    val company: CompanyEntity
)

fun UserResponse.toDomain() = User(id, name, username, email, address, phone, website, company)