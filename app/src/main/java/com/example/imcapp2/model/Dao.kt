package com.example.imcapp2.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    suspend fun insert(imcEntity: ImcEntity)

    @Query("SELECT * FROM imc_table")
    suspend fun getAll(): List<ImcEntity>
}
