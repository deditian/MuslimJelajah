package com.dedi.muslimjelajah.domain.entity

import com.google.gson.annotations.SerializedName

data class Surah(
    val arti: String,
    val asma: String,
    val ayat: String,
    val nama: String,
    val type: String,
    val urut: String,
    val audio: String,
    val nomor: String,
    val rukuk: String,
    val keterangan: String,
)
