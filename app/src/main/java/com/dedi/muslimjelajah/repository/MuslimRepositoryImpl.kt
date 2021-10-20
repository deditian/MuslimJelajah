package com.dedi.muslimjelajah.repository

import com.dedi.muslimjelajah.data.local.AppDao
import com.dedi.muslimjelajah.data.source.MuslimDataSource
import com.dedi.muslimjelajah.domain.Mapper
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.fetch
import com.dedi.muslimjelajah.utils.idle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MuslimRepositoryImpl @Inject constructor(
    private val dataSource: MuslimDataSource,
    private val localDataSource: AppDao
) : MuslimRepository {


    private val _surah: MutableStateFlow<ResultState<List<Surah>>> = idle()
    private val _ayah: MutableStateFlow<ResultState<List<Ayah>>> = idle()

    override val surah: StateFlow<ResultState<List<Surah>>> = _surah
    override val ayah: StateFlow<ResultState<List<Ayah>>> = _ayah
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override suspend fun getSurah() {
        CoroutineScope(coroutineContext).launch {
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

}