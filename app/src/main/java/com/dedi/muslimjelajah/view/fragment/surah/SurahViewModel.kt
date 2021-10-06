package com.dedi.muslimjelajah.view.fragment.surah

import android.util.Log
import androidx.lifecycle.*
import com.dedi.muslimjelajah.repository.MuslimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(private val repository : MuslimRepository) : ViewModel() {


    val surah = repository.surah.asLiveData(viewModelScope.coroutineContext)


    fun getSurah() = viewModelScope.launch { repository.getSurah() }

//    private var menusLocalMutableLiveData = MutableLiveData<List<MenuItem>>()

//    init {
//        getMenuPages()
//    }

//    private fun getMenuPages() {
//        val result = giveMenu()
//        Log.e("TAG", "getMenuPages: viewmodel $result " )
//        menusLocalMutableLiveData.value = giveMenu()
//    }

//    fun getMenuPagesLiveData(): LiveData<List<MenuItem>> {
//        return menusLocalMutableLiveData
//    }

//    override fun onCleared() {
//        viewModelScope.cancel()
//        super.onCleared()
//    }

//    private fun giveMenu(): List<MenuItem>  {
//        val list = mutableListOf<MenuItem>()
//        val obj = JSONObject(repository.loadJSONFromAsset())
//        val userArray: JSONArray = obj.getJSONArray("menu")
//        Log.e("TAG", "giveMenu: $userArray" )
//        // implement a loop to get colors list data
//        for (i in 0 until userArray.length()) {
//            // create a JSONObject for fetching single color data
//            val userDetail = userArray.getJSONObject(i)
//            // fetch color name and category and store it in arraylist
//            Log.e("TAG", "giveMenu: title : ${userDetail.getString("title")} ", )
//            list.add(MenuItem(
//                    userDetail.getInt("type"),
//                    userDetail.getString("title")
//            )
//            )
//            // create an object for getting code data from JSONObject
//            val contact = userDetail.getJSONArray("item")
//            for (j in 0 until contact.length()){
//                // fetch hex value and store it in arraylist
//                val itemnya = contact.getJSONObject(j)
//                list.add(
//                        MenuItem(
//                                itemnya.getInt("type"),
//                                null,
//                                ItemPage(
//                                        itemnya.getString("image_menu"),
//                                        itemnya.getString("title_menu")
//                                )
//                        ) )
//            }
//
//        }
//        Log.e("TAG", "giveMenu: menuitem  $list", )
//        return list
//    }

}