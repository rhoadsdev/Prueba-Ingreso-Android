package com.ceiba.pruebaingreso.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ceiba.pruebaingreso.data.model.AddressEntity
import com.ceiba.pruebaingreso.data.model.CompanyEntity
import com.ceiba.pruebaingreso.data.model.UserResponse

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "address") val address: AddressEntity,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "website") val website: String,
    @ColumnInfo(name = "company") val company: CompanyEntity
)

fun UserResponse.toDatabase() = UserEntity(
    name = name,
    username = username,
    email = email,
    address = address,
    phone = phone,
    website = website,
    company = company
)