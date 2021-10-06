package com.dedi.muslimjelajah.data.source

import com.dedi.muslimjelajah.data.Services
import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import javax.inject.Inject

class MuslimDataSourceImpl @Inject constructor(private val services: Services) : MuslimDataSource {
    override suspend fun surah(): SurahResponse {
        return services.surah()
    }

    override suspend fun ayah(nomor: Int): List<Ayah> {
        return services.ayah(nomor)
    }
}