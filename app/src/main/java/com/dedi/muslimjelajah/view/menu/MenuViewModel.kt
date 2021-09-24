package com.dedi.muslimjelajah.view.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedi.muslimjelajah.model.MenuItem
import kotlinx.coroutines.launch


class MenuViewModel(private val repository : MenuRepository) : ViewModel() {

    private var menusLocalMutableLiveData = MutableLiveData<MutableList<MenuItem>>()
    val menusLocalLiveData: LiveData<MutableList<MenuItem>> = menusLocalMutableLiveData

    fun getMenuPages() = viewModelScope.launch {
//        val menus = MutableLiveData<MutableList<MenuItem>>(MenuItem.getMenus())
        val result = repository.giveMenu()

//        val itemPage = repository.giveItem()
        Log.e("TAG", "getMenuPages: viewmodel $result " )
//        Log.e("TAG", "getITEMPages: viewmodel $itemPage " )
        menusLocalMutableLiveData.postValue(result)
    }

    fun getItem(){

    }

}