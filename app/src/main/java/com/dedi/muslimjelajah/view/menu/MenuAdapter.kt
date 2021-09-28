package com.dedi.muslimjelajah.view.menu

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dedi.muslimjelajah.databinding.HeaderLayoutBinding
import com.dedi.muslimjelajah.databinding.MenuLayoutBinding
import com.dedi.muslimjelajah.model.ItemPage
import com.dedi.muslimjelajah.model.MenuItem


class MenuAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _menusList : MutableList<MenuItem> = mutableListOf()
    private val menusList: List<MenuItem>
        get() = _menusList

    companion object {
        const val ITEM_HEADER = 0
        const val ITEM_MENU = 1
    }

    class MenuHeaderViewHolder(val binding: HeaderLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    class MenuItemViewHolder(val binding: MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun getItemViewType(position: Int): Int {
        return menusList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return when (viewType) {
            ITEM_HEADER -> MenuHeaderViewHolder(HeaderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ITEM_MENU -> MenuItemViewHolder(MenuLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Unsupported layout") // in case populated with a model we don't know how to display.
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                (holder as MenuHeaderViewHolder).bind(menusList[position])
            }
            ITEM_MENU -> {
                (holder as MenuItemViewHolder).bind(menusList[position].item)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int {
        return menusList.size
    }

    fun setList(menuItem: List<MenuItem>) {
        _menusList.addAll(menuItem)
        notifyDataSetChanged()
    }

    private fun MenuHeaderViewHolder.bind(menus: MenuItem) {
        binding.txtCategoryTitleMenu.text = menus.title
    }

    private fun MenuItemViewHolder.bind(item : ItemPage?){
        binding.imgMenu.setImageDrawable(drawableNew(item!!.image_menu))
        binding.tvTitleMenu.text = item.title_menu
    }

    private fun drawableNew(filename: String) : Drawable{
        val inputStream = this.context.assets.open(filename)
        return Drawable.createFromStream(inputStream, null)
    }

}