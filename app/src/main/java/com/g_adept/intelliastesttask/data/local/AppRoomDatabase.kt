package com.g_adept.intelliastesttask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.g_adept.intelliastesttask.data.entities.Word

@Database(entities = [Word::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDataBase(appContext: Context) =
            Room.databaseBuilder(appContext, AppRoomDatabase::class.java, "app_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}