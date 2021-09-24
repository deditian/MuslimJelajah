package com.dedi.muslimjelajah.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class MenuItem(
        val type: Int,
        val menu: String? = null,
        val item: ItemPage? = null,
)

data class MenuPage (
    @SerializedName("title") val title: String,
    @SerializedName("item") val item: List<ItemPage>
)

data class ItemPage (
    @SerializedName("image_menu") val image_menu: String,
    @SerializedName("title_menu") val title_menu: String
)