package com.dedi.muslimjelajah.domain.entity

import androidx.room.Entity

@Entity(tableName = "ayah_table")
data class Ayah(
    val ar: String,
    val id: String,
    val tr: String,
    val nomor: String
)
