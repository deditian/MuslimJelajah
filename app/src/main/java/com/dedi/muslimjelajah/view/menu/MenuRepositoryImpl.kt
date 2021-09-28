package com.dedi.muslimjelajah.view.menu

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset


class MenuRepositoryImpl(private val mContext: Context) : MenuRepository {

    override fun loadJSONFromAsset(): String {
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