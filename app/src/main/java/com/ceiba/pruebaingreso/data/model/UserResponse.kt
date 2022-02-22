package com.ceiba.pruebaingreso.data.model

data class UserResponse(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressEntity,
    val phone: String,
    val website: String,
    val company: CompanyEntity
)