package com.example.focusstart9.domain.entity.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.focusstart9.domain.entity.db.StringArrayListConverter

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey
    val name: String,
    @TypeConverters(StringArrayListConverter::class)
    val phoneNumberList: ArrayList<String>
)