package com.ordolabs.data_local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ordolabs.data_local.database.model.ColorHistoryEntity

@Dao
interface ColorsHistoryDao {

    @Transaction
    @Query("SELECT * FROM colors_history")
    suspend fun getAll(): List<ColorHistoryEntity>
}