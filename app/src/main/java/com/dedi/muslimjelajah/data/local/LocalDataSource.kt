package com.dedi.muslimjelajah.data.local

import androidx.lifecycle.LiveData
import com.dedi.muslimjelajah.db.dao.AppDao
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Surah
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 10 August 2020
 */

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val appDao: AppDao) {


    suspend fun deleteSurah() {
        return appDao.deleteAllSurah()
    }

    suspend fun saveSurah(surah: List<Surah>) {
        appDao.insertSurah(surah)
    }

    suspend fun getAllSurahs(): ResultState<List<Surah>> {
        return ResultState.Success(appDao.getAllSurah())
    }
}