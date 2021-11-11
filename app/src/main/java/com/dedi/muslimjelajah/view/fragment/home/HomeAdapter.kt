package com.dedi.muslimjelajah.view.fragment.home

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dedi.muslimjelajah.databinding.MenuItemLayoutBinding
import com.dedi.muslimjelajah.domain.entity.ItemPage
import com.dedi.muslimjelajah.domain.entity.MenuItem


class HomeAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _menusList : MutableList<MenuItem> = mutableListOf()
    private val menusList: List<MenuItem>
        get() = _menusList

    class MenuItemViewHolder(val binding: MenuItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuItemViewHolder(MenuItemLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MenuItemViewHolder).bind(menusList[position].item)
    }

    override fun getItemCount(): Int {
        return menusList.size
    }

    private fun MenuItemViewHolder.bind(menus: ItemPage?) = binding.run {
        if (menus != null) {
            imgItem.load(drawableNew(menus.image_menu)){
                crossfade(false)
                transformations(CircleCropTransformation())
            }
            tvItem.text = menus.title_menu
        } else throw IllegalArgumentException("Undefined view item page")
    }

    fun setList(menuItem: List<MenuItem>) {
        _menusList.clear()
        _menusList.addAll(menuItem)
        notifyDataSetChanged()
    }

    private fun drawableNew(filename: String) : Drawable{
        val inputStream = this.context!!.assets.open(filename)
        return Drawable.createFromStream(inputStream, null)
    }
}