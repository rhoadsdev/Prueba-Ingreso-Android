package com.ceiba.pruebaingreso.di

import com.ceiba.pruebaingreso.data.network.PostApiClient
import com.ceiba.pruebaingreso.data.network.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =
            OkHttpClient.Builder()
                /*.addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Basic MDAwMTIzMDAwQUJD")
                        .build()
                    chain.proceed(request)
                }*/
                .addInterceptor(interceptor)
                .addInterceptor(Interceptor { chain ->
                    val request: Request = chain.request()
                    val response = chain.proceed(request)
                    // todo deal with the issues the way you need to
                    if (response.code == 500) {
                        /*startActivity(
                            Intent(
                                this@ErrorHandlingActivity,
                                ServerIsBrokenActivity::class.java
                            )
                        )*/
                        return@Interceptor response
                    }
                    response
                }).build()
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiClient(retrofit: Retrofit): UserApiClient {
        return retrofit.create(UserApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providePostApiClient(retrofit: Retrofit): PostApiClient {
        return retrofit.create(PostApiClient::class.java)
    }
}