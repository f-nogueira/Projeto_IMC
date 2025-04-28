package com.example.imcapp2.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImcEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun Dao(): Dao
}
