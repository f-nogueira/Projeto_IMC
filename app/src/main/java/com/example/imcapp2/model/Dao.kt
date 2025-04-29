package com.example.imcapp2.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface Dao {
    @Insert
    suspend fun insert(imcEntity: ImcEntity)

    @Query("SELECT * FROM imc_table ORDER BY id DESC")
    suspend fun getAll(): List<ImcEntity>

    @Update
    suspend fun update(imcEntity: ImcEntity)

    @Delete
    suspend fun delete(imcEntity: ImcEntity)
}
