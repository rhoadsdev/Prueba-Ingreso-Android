package com.ceiba.pruebaingreso.di

import android.content.Context
import androidx.room.Room
import com.ceiba.pruebaingreso.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PRUEBA_DATABASE_NAME = "prueba_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        PRUEBA_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: UserDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun providePostDao(db: UserDatabase) = db.getPostDao()

}