package com.dedi.muslimjelajah.view.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedi.muslimjelajah.domain.entity.ItemPage
import com.dedi.muslimjelajah.domain.entity.MenuItem
import com.dedi.muslimjelajah.repository.MuslimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MuslimRepository) : ViewModel() {

    private var menusLocalMutableLiveData = MutableLiveData<List<MenuItem>>()
    val menusLocalLiveData: LiveData<List<MenuItem>> = menusLocalMutableLiveData


    init {
        getMenuPages()
    }


    fun getMenuPages() = viewModelScope.launch {
        //        val menus = MutableLiveData<MutableList<MenuItem>>(MenuItem.getMenus())
        val result = giveMenu()

        //        val itemPage = repository.giveItem()
        Log.e("TAG", "getMenuPages: viewmodel $result ")
        //        Log.e("TAG", "getITEMPages: viewmodel $itemPage " )
        menusLocalMutableLiveData.value = result
    }


    private fun giveMenu(): List<MenuItem> {
        val list = mutableListOf<MenuItem>()
        val obj = JSONObject(repository.getMenu())
        val userArray: JSONArray = obj.getJSONArray("menu")
        Log.e("TAG", "giveMenu: $userArray")
        // implement a loop to get colors list data
        for (i in 0 until userArray.length()) {
            // create a JSONObject for fetching single color data
            val userDetail = userArray.getJSONObject(i)
            // fetch color name and category and store it in arraylist
//            Log.e("TAG", "giveMenu: title : ${userDetail.getString("title")} ")
//            list.add(
//                MenuItem(
//                    userDetail.getInt("type"),
//                    userDetail.getString("title")
//                )
//            )
            // create an object for getting code data from JSONObject
            val contact = userDetail.getJSONArray("item")
            for (j in 0 until contact.length()) {
                // fetch hex value and store it in arraylist
                val itemnya = contact.getJSONObject(j)
                list.add(
                    MenuItem(
                        itemnya.getInt("type"),
                        null,
                        ItemPage(
                            itemnya.getString("image_menu"),
                            itemnya.getString("title_menu")
                        )
                    )
                )
            }

        }
        Log.e("TAG", "giveMenu: menuitem  $list")
        return list
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}