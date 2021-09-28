package com.dedi.muslimjelajah.model

import com.google.gson.annotations.SerializedName


data class MenuItem(
        val type: Int,
        val title: String? = null,
        val item: ItemPage? = null,
)

data class ItemPage (
    @SerializedName("image_menu") val image_menu: String,
    @SerializedName("title_menu") val title_menu: String
)