package com.example.myapplication_final

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: BoredData)

    @Delete
    suspend fun delete(data: BoredData)
}