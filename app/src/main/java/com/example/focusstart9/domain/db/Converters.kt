package com.example.focusstart9.domain.db

import androidx.room.TypeConverter

class StringArrayListConverter {
    @TypeConverter
    fun fromString(stringArrayListString: String): ArrayList<String> {
        return stringArrayListString.split(",").mapTo(ArrayList()) { it }
    }

    @TypeConverter
    fun toString(stringArrayList: ArrayList<String>): String {
        return stringArrayList.joinToString(separator = ",")
    }
}