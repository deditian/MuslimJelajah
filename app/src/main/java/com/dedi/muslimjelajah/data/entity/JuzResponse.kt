package com.dedi.muslimjelajah.data.entity

import com.dedi.muslimjelajah.domain.entity.Juz

data class JuzResponse(
        val type: Int,
        val title: String? = null,
        val item: Juz? = null,
)

