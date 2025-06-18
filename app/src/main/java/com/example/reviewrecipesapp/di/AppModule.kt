package com.example.reviewrecipesapp.di

import com.example.reviewrecipesapp.common.ApiConstants
import com.example.reviewrecipesapp.data.repository.RecipesRepository
import com.example.reviewrecipesapp.data.repository.RecipesRepositoryImpl
import com.example.reviewrecipesapp.data.service.RecipesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RecipesRepositoryImpl
    ) : RecipesRepository

    companion object {

        @Provides
        @Singleton
        fun provideOkHttp() : OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }


        @Provides
        @Singleton
        fun provideRetrofit(
            client : OkHttpClient
        ) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideService(
            retrofit: Retrofit
        ) : RecipesService {
            return retrofit.create(RecipesService::class.java)
        }

        @Provides
        @Singleton
        fun provideDispatcher() : CoroutineDispatcher = Dispatchers.IO
    }
}