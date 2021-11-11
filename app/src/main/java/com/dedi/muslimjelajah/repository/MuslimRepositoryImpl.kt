package com.dedi.muslimjelajah.repository

import android.content.Context
import com.dedi.muslimjelajah.data.local.AppDao
import com.dedi.muslimjelajah.data.source.MuslimDataSource
import com.dedi.muslimjelajah.domain.Mapper
import com.dedi.muslimjelajah.domain.Resource
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.NewsArticle
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.MENU_FILE
import com.dedi.muslimjelajah.utils.fetch
import com.dedi.muslimjelajah.utils.idle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MuslimRepositoryImpl @Inject constructor(
    private val mContext: Context,
    private val dataSource: MuslimDataSource,
    private val localDataSource: AppDao
) : MuslimRepository {


    private val _surah: MutableStateFlow<ResultState<List<Surah>>> = idle()
    private val _ayah: MutableStateFlow<ResultState<List<Ayah>>> = idle()

    override val surah: StateFlow<ResultState<List<Surah>>> = _surah
    override val ayah: StateFlow<ResultState<List<Ayah>>> = _ayah

    override suspend fun getSurah() {
            fetch {
                val local = localDataSource.getAllSurah()
                if (local.isNullOrEmpty()){
                    val surahSource = dataSource.surah()
                    localDataSource.insertAllSurah(surahSource.data)
                    localDataSource.getAllSurah()
                }else{
                    localDataSource.getAllSurah()
                }
            }.map {
                Mapper.mapResult(it) {
                    map { surah ->
                        Mapper.mapApiToEntity(surah)
                    }
                }
            }.collect {
                _surah.value = it
            }
    }

    override suspend fun getAyah(nomor: Int) {
        fetch {
            dataSource.ayah(nomor)
        }.map {
            Mapper.mapResult(it) {
                map { ayah ->
                    Mapper.mapApiToEntity(ayah)
                }
            }
        }.collect {
            _ayah.value = it
        }
    }

    override fun getMenu(): String {
        val json: String?
        try {
            val inputStream = mContext.assets.open(MENU_FILE)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                val charset: Charset = Charsets.UTF_8
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, charset)
            }
        catch (ex: IOException) {
                ex.printStackTrace()
                return ""
            }
        return json
    }

    override fun getBreakingNews(
        forceRefresh: Boolean,
        onFetchSuccess: () -> Unit,
        onFetchFailed: (Throwable) -> Unit
    ): Flow<Resource<List<NewsArticle>>> {
        TODO("Not yet implemented")
    }

}