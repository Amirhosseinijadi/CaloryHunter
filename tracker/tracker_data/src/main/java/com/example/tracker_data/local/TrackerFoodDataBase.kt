package com.example.tracker_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tracker_data.local.entity.TrackedFoodEntity

@Database(entities = [TrackedFoodEntity::class], version = 1, exportSchema = false)
abstract class TrackerFoodDataBase :RoomDatabase(){
    abstract val dao: TrackerFoodDao
}