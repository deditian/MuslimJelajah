package com.dedi.muslimjelajah.domain

import com.dedi.muslimjelajah.data.entity.SurahResponse
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.onSuccess
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object Mapper {

    fun mapApiToEntity(item: Surah): Surah {
        return Surah(item.arti, item.asma,item.ayat,item.nama, item.type,item.urut, item.audio,item.nomor, item.rukuk,item.keterangan)
    }

    fun mapApiToEntity(item: Ayah): Ayah {
        return Ayah(item.ar,item.id,item.tr,item.nomor)
    }

//    suspend inline fun <T : Any> mapResultToData(resultState: ResultState<T>): ResultState<T>? =
//        suspendCancellableCoroutine { task ->
//            resultState.onSuccess {
//                val data = ResultState.Success(it)
//                try {
//                    task.resume(data)
//                } catch (e: Throwable) {
//                    e.printStackTrace()
//                    task.resume(null)
//                }
//            }
//        }

    inline fun <T: Any, U: Any> mapResult(resultState: ResultState<out T>, mapper: T.() -> U): ResultState<U> {
        return when (resultState) {
            is ResultState.Success -> {
                val data = resultState.data
                val mapData = mapper.invoke(data)
                ResultState.Success(mapData)
            }
            is ResultState.Idle -> ResultState.Idle()
            is ResultState.Error -> ResultState.Error(resultState.throwable)
            is ResultState.Loading -> ResultState.Loading()
        }
    }
}