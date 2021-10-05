package com.dedi.muslimjelajah.view.fragment.surah

import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.FragmentSurahBinding
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.onFailure
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

            networkUI(result)

            result.onSuccess { surah ->
                Log.e("TAG", "onView: asd surah $result" )
                surahAdapter.setList(surah)
            }

        }

    }

    private fun FragmentSurahBinding.networkUI(result: ResultState<List<Surah>>) = layoutNetwork.run {
        val isLoading = result is ResultState.Loading
        val isError = result is ResultState.Error
        btnTryAgain.setOnClickListener {
            viewModel.getSurah()
        }

        prNetwork.isVisible = isLoading
        btnTryAgain.isVisible = isError
        tvError.isVisible = isError

        result.onFailure { th ->
            tvError.text = th.message
        }
    }


}