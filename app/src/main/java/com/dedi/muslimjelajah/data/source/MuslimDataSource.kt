package com.dedi.muslimjelajah.data.source

import com.dedi.muslimjelajah.data.entity.SurahResponse

interface MuslimDataSource {
    suspend fun surah(): SurahResponse
}