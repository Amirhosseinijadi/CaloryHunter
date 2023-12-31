package com.example.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerFoodDao {

    @Insert
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood (trackedFoodEntity: TrackedFoodEntity)

    @Query(
        """
            SELECT *
            FROM trackedfoodentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getFoodsForDate (day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}