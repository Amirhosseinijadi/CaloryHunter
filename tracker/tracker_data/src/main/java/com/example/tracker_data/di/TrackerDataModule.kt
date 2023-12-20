package com.example.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.example.tracker_data.local.TrackerFoodDataBase
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_data.repository.TrackerRepositoryImpl
import com.example.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkhttp():OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            }
        ).build()
    }
    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient):OpenFoodApi{
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerDataBase(app:Application):TrackerFoodDataBase{
        return Room.databaseBuilder(
            app,
            TrackerFoodDataBase::class.java,
            "tracker_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideTrackerRepository(
        db:TrackerFoodDataBase,
        api: OpenFoodApi
    ):TrackerRepository{
        return TrackerRepositoryImpl(
            db = db.dao,
            api = api
        )
    }
}