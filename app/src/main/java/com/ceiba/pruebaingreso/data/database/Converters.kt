package com.ceiba.pruebaingreso.data.database

import androidx.room.TypeConverter
import com.ceiba.pruebaingreso.data.model.AddressEntity
import com.ceiba.pruebaingreso.data.model.CompanyEntity
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromAddress(value: AddressEntity): String = Gson().toJson(value)

    @TypeConverter
    fun toAddress(value: String): AddressEntity = Gson().fromJson(value, AddressEntity::class.java)

    @TypeConverter
    fun fromCompany(value: CompanyEntity): String = Gson().toJson(value)

    @TypeConverter
    fun toCompany(value: String): CompanyEntity = Gson().fromJson(value, CompanyEntity::class.java)

}