package com.dedi.muslimjelajah.data.source

import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.entity.Ayah

interface MuslimDataSource {
    suspend fun surah(): SurahResponse
    suspend fun ayah(nomor : Int): List<Ayah>
}