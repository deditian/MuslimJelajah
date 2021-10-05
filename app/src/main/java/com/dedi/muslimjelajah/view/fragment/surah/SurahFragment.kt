package com.dedi.muslimjelajah.view.fragment.surah

import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.FragmentSurahBinding
import com.dedi.muslimjelajah.utils.onSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurahFragment : Fragment(R.layout.fragment_surah) {

    private val viewModel: SurahViewModel by activityViewModels()
    private val surahAdapter = SurahAdapter()
    private val binding : FragmentSurahBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onView()
    }

    private fun onView() = binding.run {

        recyclerViewSurah.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = surahAdapter
        }

        viewModel.surah.observe(viewLifecycleOwner){ result ->

            result.onSuccess { surah ->
                Log.e("TAG", "onView: asd surah $result" )
                surahAdapter.setList(surah)
            }

        }

    }


}