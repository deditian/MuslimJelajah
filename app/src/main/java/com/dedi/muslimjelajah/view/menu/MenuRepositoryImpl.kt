package com.dedi.muslimjelajah.view.menu

import android.content.Context
import android.util.Log
import com.dedi.muslimjelajah.model.ItemPage
import com.dedi.muslimjelajah.model.MenuItem
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class MenuRepositoryImpl(private val mContext: Context) : MenuRepository {
    private val list = mutableListOf<MenuItem>()
//    private val menuPage = mutableListOf<String>()
//    private val itemPage = mutableListOf<ItemPage>()


    override fun giveMenu(): MutableList<MenuItem>  {
        val obj = JSONObject(loadJSONFromAsset())
        val userArray: JSONArray = obj.getJSONArray("menu")
        Log.e("TAG", "giveMenu: $userArray" )
        var type = 0
        // implement a loop to get colors list data
        for (i in 0 until userArray.length()) {
            // create a JSONObject for fetching single color data
            val userDetail = userArray.getJSONObject(i)
            // fetch color name and category and store it in arraylist
            Log.e("TAG", "giveMenu: title : ${userDetail.getString("title")} ", )
            list.add(MenuItem(
                    userDetail.getInt("type"),
                    userDetail.getString("title")
            )
            )
//            type = userDetail.getInt("type")

//            Log.e("TAG", "giveMenu: menupage $", )
            // create an object for getting code data from JSONObject
            val contact = userDetail.getJSONArray("item")
                for (j in 0 until contact.length()){
                    // fetch hex value and store it in arraylist
                        val itemnya = contact.getJSONObject(j)

//                    Log.e("TAG", "giveMenu: contact $itemnya ", )
                    type = itemnya.getInt("type")
                    list.add(
                            MenuItem(
                                    itemnya.getInt("type"),
                                    null,
                                    ItemPage(
                                            itemnya.getString("image_menu"),
                                            itemnya.getString("title_menu")
                                    )
                                     ) )
//                    Log.e("TAG", "giveMenu: itempage $itemPage ", )

                }

        }

//        list.add(MenuItem(type, menuPage, itemPage))
        Log.e("TAG", "giveMenu: menuitem  $list", )
//        Log.e("TAG", "loadJSONFromAsset(): ${loadJSONFromAsset()}")
//        val listMenuType = object : TypeToken<List<MenuPage>>() {}.type
//        val menu = JSONObject(loadJSONFromAsset()).getJSONArray("menu")
//        Log.e("TAG", "menu loadJSONFromAsset(): ${menu}")
//        val llkm : List<MenuPage> = Gson().fromJson(menu, listMenuType)

        return list
    }

//    override fun giveItem(): List<ItemPage> {
//        val listMenuType = object : TypeToken<List<ItemPage>>() {}.type
//        val menu = JSONObject(loadJSONFromAsset()).getJSONArray("menu")
//
////        val asd = item.getJSONArray("item").length()
////        val arrayjson = menu.getJSONArray("item").toString()
////        Log.e("TAG", "giveMenuITEMdedi  asd : $asd" )
////        Log.e("TAG", "giveMenuITEMdedi: $item" )
//        val asd : List<ItemPage> = Gson().fromJson(item.toString(), listMenuType)
//        Log.e("TAG", "giveMenuITEMdedi  ASD: $asd")
//        //        menuPages.forEachIndexed { idx, person -> Log.i("dataDedi", "> Item $idx:\n$person") }
//        return asd
//    }


    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = mContext.assets.open("menu_resource.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}