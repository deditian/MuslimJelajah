package com.dedi.muslimjelajah.repository.networkdata

import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MuslimRepository {
    val surah: StateFlow<ResultState<List<Surah>>>
    val ayah: StateFlow<ResultState<List<Ayah>>>

    suspend fun getSurah()
    suspend fun getAyah(nomor: Int)
}