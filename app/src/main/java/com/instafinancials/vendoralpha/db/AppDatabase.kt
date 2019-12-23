package com.instafinancials.vendoralpha.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BookmarkDataForDb::class], version = 1)
@TypeConverters(DateTypeConverter::class, ListConverter::class, JsonObjectConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkDataDao(): BookmarkDataDao

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