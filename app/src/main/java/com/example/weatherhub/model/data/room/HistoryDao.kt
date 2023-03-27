package com.example.weatherhub.model.data.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Query("SELECT * FROM history_table")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history_table WHERE city LIKE :city")
    fun getHistoryForCity(city: String): List<HistoryEntity>
}