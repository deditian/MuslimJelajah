package com.dedi.muslimjelajah.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah

@Dao
interface AppDao {




    @Query("SELECT * FROM surah_table")
    fun getAllSurah(): List<Surah>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurah(ayah: List<Surah>)

    @Query("DELETE FROM surah_table")
    fun deleteAllSurah()

}