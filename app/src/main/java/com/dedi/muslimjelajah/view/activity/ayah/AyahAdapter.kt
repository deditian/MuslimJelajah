package com.dedi.muslimjelajah.view.activity.ayah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dedi.muslimjelajah.databinding.ItemAyahBinding
import com.dedi.muslimjelajah.domain.entity.Ayah

class AyahAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var _ayahList : MutableList<Ayah> = mutableListOf()
    private val ayahList: List<Ayah>
        get() = _ayahList

    class AyahViewHolder(val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AyahViewHolder(ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AyahViewHolder).bind(ayahList[position])
    }

    override fun getItemCount(): Int {
        return ayahList.size
    }

    fun setList(surahItem: List<Ayah>) {
        _ayahList.clear()
        _ayahList.addAll(surahItem)
        notifyDataSetChanged()
    }

    private fun AyahViewHolder.bind(item: Ayah) {
        binding.run {
            txtAyaharab.text = item.ar
            txtNumberinsurah.text = item.nomor
            txtArti.text = item.id
        }
    }

}