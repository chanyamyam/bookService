package com.app.part3.bookservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.part3.bookservice.dao.HistoryDao
import com.app.part3.bookservice.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}