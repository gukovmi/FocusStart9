package com.example.focusstart9.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.focusstart9.domain.models.Contact

@Database(entities = [(Contact::class)], version = 1, exportSchema = false)
@TypeConverters(StringArrayListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}