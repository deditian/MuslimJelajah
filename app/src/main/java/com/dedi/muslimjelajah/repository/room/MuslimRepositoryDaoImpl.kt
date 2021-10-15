package com.dedi.muslimjelajah.repository.room

import com.dedi.muslimjelajah.data.local.LocalDataSource
import com.dedi.muslimjelajah.domain.entity.Surah

class MuslimRepositoryDaoImpl(val localDataSource: LocalDataSource) : MuslimRepositoryDao {
    override fun insert(entity: Surah): Surah {
        TODO("Not yet implemented")
    }

    override fun get(): List<Surah> {
        TODO("Not yet implemented")
    }


}