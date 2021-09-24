package com.dedi.muslimjelajah.view.menu

import android.content.Context
import com.dedi.muslimjelajah.model.ItemPage
import com.dedi.muslimjelajah.model.MenuItem
import com.dedi.muslimjelajah.model.MenuPage

interface MenuRepository {
    fun  giveMenu(): MutableList<MenuItem>
//    fun  giveItem(): List<ItemPage>
}