package com.dedi.muslimjelajah.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.db.dao.AppDao
import com.dedi.muslimjelajah.domain.entity.Ayah

@Database(entities = [SurahResponse::class, Ayah::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAppDao(): AppDao
}