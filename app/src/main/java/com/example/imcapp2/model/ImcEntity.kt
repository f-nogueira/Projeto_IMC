package com.example.imcapp2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imc_table")
data class ImcEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val altura: Float,
    val peso: Float,
    val imc: Float
)
