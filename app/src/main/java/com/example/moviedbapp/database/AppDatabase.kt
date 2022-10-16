package com.example.moviedbapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviedbapp.converter.DateTypeConverter
import com.example.moviedbapp.converter.ListConverter
import com.example.moviedbapp.dao.RoutineDao
import com.example.moviedbapp.entity.QuestionsResult

@Database(entities = [QuestionsResult::class], version = 1)
@TypeConverters(DateTypeConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    companion object {
        var INSTANCE: AppDatabase? = null
        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}