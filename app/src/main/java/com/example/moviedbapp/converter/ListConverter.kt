package com.example.moviedbapp.converter

import androidx.room.TypeConverter
import com.example.moviedbapp.entity.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
class ListConverter {
    var gson = Gson()
    @TypeConverter
    fun fromTimestamp(data: String?): List<Result>? {
        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }
    @TypeConverter
    fun someObjectListToString(someObjects: List<Result>?): String? {
        return gson.toJson(someObjects)
    }

}