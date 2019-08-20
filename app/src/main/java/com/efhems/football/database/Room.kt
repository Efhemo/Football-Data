package com.efhems.football.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.efhems.football.model.Competitions


@Database(entities = [Competitions::class], version = 1, exportSchema = false)
abstract class FootballDatabase : RoomDatabase() {
    abstract val dao: Dao
}

private lateinit var INSTANCE: FootballDatabase

fun getDatabase(context: Context): FootballDatabase {
    synchronized(FootballDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                FootballDatabase::class.java, "Football").build()
        }
    }
    return INSTANCE
}

