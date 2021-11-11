package com.dedi.muslimjelajah.view.fragment.home.news

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dedi.muslimjelajah.databinding.ItemNewsLayoutBinding
import com.dedi.muslimjelajah.databinding.MenuItemLayoutBinding
import com.dedi.muslimjelajah.domain.entity.ItemPage
import com.dedi.muslimjelajah.domain.entity.MenuItem
import com.dedi.muslimjelajah.domain.entity.NewsArticle


class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _menusList : MutableList<NewsArticle> = mutableListOf()
    private val menusList: List<NewsArticle>
        get() = _menusList

    class NewsViewHolder(val binding: ItemNewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(ItemNewsLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(menusList[position])
    }

    override fun getItemCount(): Int {
        return menusList.size
    }

    private fun NewsViewHolder.bind(menus: NewsArticle) = binding.run {
        if (menus != null) {
            imageView.load(menus.thumbnailUrl){
                crossfade(false)
//                transformations(CircleCropTransformation())
            }
            textViewTitle.text = menus.title
        } else throw IllegalArgumentException("Undefined view item page")
    }

    fun setList(menuItem: List<NewsArticle>) {
        _menusList.clear()
        _menusList.addAll(menuItem)
        notifyDataSetChanged()
    }
}