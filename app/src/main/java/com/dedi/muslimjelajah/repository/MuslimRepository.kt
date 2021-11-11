package com.dedi.muslimjelajah.repository

import android.content.Context
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.Resource
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.NewsArticle
import com.dedi.muslimjelajah.domain.entity.Surah
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MuslimRepository {
    val surah: StateFlow<ResultState<List<Surah>>>
    val ayah: StateFlow<ResultState<List<Ayah>>>

    suspend fun getSurah()
    suspend fun getAyah(nomor: Int)

    fun getMenu(): String

    fun getBreakingNews(
        forceRefresh: Boolean,
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit
    ): Flow<Resource<List<NewsArticle>>>
}