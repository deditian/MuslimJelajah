package com.dedi.muslimjelajah.repository.networkdata

import com.dedi.muslimjelajah.data.source.MuslimDataSource
import com.dedi.muslimjelajah.db.dao.AppDao
import com.dedi.muslimjelajah.domain.Mapper
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.fetch
import com.dedi.muslimjelajah.utils.idle
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class MuslimRepositoryImpl @Inject constructor(private val dataSource: MuslimDataSource, private val dao: AppDao) : MuslimRepository {


    private val _surah: MutableStateFlow<ResultState<List<Surah>>> = idle()
    private val _ayah: MutableStateFlow<ResultState<List<Ayah>>> = idle()

    override val surah: StateFlow<ResultState<List<Surah>>> = _surah
    override val ayah: StateFlow<ResultState<List<Ayah>>> = _ayah

    override suspend fun getSurah() {
        fetch{
            dataSource.surah()
        }.map {
            Mapper.mapResult(it) {
                data.map { surah ->
                    Mapper.mapApiToEntity(surah)
                }
            }
        }.collect {
            _surah.value = it

        }
    }

    override suspend fun getAyah(nomor: Int) {
        fetch{
//            dataSource.ayah(nomor)
//            dao.insertAyahs(dataSource.ayah(nomor))
//            dao.getAllAyahs()
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