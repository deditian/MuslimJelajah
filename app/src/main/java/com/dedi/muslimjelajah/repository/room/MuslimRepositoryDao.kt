package com.dedi.muslimjelajah.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dedi.muslimjelajah.domain.entity.Surah

@Dao
interface MuslimRepositoryDao {
    fun insert(entity: Surah): Surah

    fun get(): List<Surah>
}