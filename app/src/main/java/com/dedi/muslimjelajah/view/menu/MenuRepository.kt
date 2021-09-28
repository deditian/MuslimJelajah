package com.dedi.muslimjelajah.view.menu

import com.dedi.muslimjelajah.model.MenuItem

interface MenuRepository {
    fun  giveMenu(): List<MenuItem>
}