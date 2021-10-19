package com.dedi.muslimjelajah.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "surah_table")
@Parcelize
data class Surah (
    val arti: String,
    val asma: String,
    val ayat: String,
    val nama: String,
    val type: String,
    val urut: String,
    val audio: String,
    @PrimaryKey
    val nomor: String,
    val rukuk: String,
    val keterangan: String,
) : Parcelable


