package com.dedi.muslimjelajah.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dedi.muslimjelajah.domain.entity.Surah

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSurah(characters: List<Surah>)

    @Query("SELECT * FROM surah_table")
    fun getAllSurah() : List<Surah>

}