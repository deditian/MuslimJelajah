package com.dedi.muslimjelajah.view.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedi.muslimjelajah.model.MenuItem
import kotlinx.coroutines.launch


class MenuViewModel(private val repository : MenuRepository) : ViewModel() {

    private var menusLocalMutableLiveData = MutableLiveData<List<MenuItem>>()
//    val menusLocalLiveData: LiveData<MutableList<MenuItem>> get() = menusLocalMutableLiveData

    init {
        getMenuPages()
    }

    private fun getMenuPages() = viewModelScope.launch {
        val result = repository.giveMenu()
        Log.e("TAG", "getMenuPages: viewmodel $result " )
        menusLocalMutableLiveData.postValue(result)
    }

    fun getMenuPagesLiveData(): LiveData<List<MenuItem>> {   // Simple getter
        return menusLocalMutableLiveData
    }

}