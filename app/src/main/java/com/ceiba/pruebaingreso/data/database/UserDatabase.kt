package com.ceiba.pruebaingreso.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceiba.pruebaingreso.data.database.dao.PostDao
import com.ceiba.pruebaingreso.data.database.dao.UserDao
import com.ceiba.pruebaingreso.data.database.entities.PostEntity
import com.ceiba.pruebaingreso.data.database.entities.UserEntity

@TypeConverters(Converters::class)
@Database(entities = [UserEntity::class, PostEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getPostDao(): PostDao
}