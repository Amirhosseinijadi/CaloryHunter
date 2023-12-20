package com.example.caloryhunter.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.core.data.prefences.DefaultPreferences
import com.example.core.domain.prefences.Prefences
import com.example.core.domain.use_case.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShredPrefrences(
        app:Application
    ):SharedPreferences{
        return app.getSharedPreferences("shared_pref",MODE_PRIVATE)
    }
    @Provides
    @Singleton
    fun providePrefences(sharedPreferences: SharedPreferences):Prefences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase():FilterOutDigits{
        return FilterOutDigits()

    }




}