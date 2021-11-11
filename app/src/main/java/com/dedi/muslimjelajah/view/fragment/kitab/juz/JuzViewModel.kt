package com.dedi.muslimjelajah.view.fragment.kitab.juz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dedi.muslimjelajah.repository.MuslimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JuzViewModel @Inject constructor(private val repository : MuslimRepository) : ViewModel() {
    val ayah = repository.ayah.asLiveData(viewModelScope.coroutineContext)

    fun getayah(nomor : Int) = viewModelScope.launch { repository.getAyah(nomor) }
}