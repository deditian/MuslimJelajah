package com.dedi.muslimjelajah.repository

import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.entity.Surah
import kotlinx.coroutines.flow.StateFlow

interface MuslimRepository {
    val surah: StateFlow<ResultState<List<Surah>>>

    suspend fun getSurah()
}