package com.dedi.muslimjelajah.view.fragment.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dedi.muslimjelajah.databinding.ItemSurahBinding
import com.dedi.muslimjelajah.domain.entity.Surah


class SurahAdapter(private val onAyahClick: (Surah) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _surahList : MutableList<Surah> = mutableListOf()
    private val surahList: List<Surah>
        get() = _surahList

    class SurahViewHolder(val binding: ItemSurahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return SurahViewHolder(ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SurahViewHolder).bind(surahList[position])
    }

    override fun getItemCount(): Int {
        return surahList.size
    }

    fun setList(surahItem: List<Surah>) {
        _surahList.clear()
        _surahList.addAll(surahItem)
        notifyDataSetChanged()
    }

    private fun SurahViewHolder.bind(surahItem: Surah) {
        binding.run {
            txtAsma.text = surahItem.asma
            txtAyat.text = surahItem.ayat
            txtNama.text = surahItem.nama
            txtNumber.text = surahItem.nomor
            txtType.text = surahItem.type
            itemContainer.setOnClickListener { onAyahClick(surahItem)  }
        }

    }

}