package com.dedi.muslimjelajah.view.activity.ayah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.Log
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.muslimjelajah.databinding.ActivityAyahBinding
import com.dedi.muslimjelajah.databinding.FragmentSurahBinding
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.onFailure
import com.dedi.muslimjelajah.utils.onSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AyahActivity : AppCompatActivity() {

    private val binding : ActivityAyahBinding by viewBinding()
    private val viewModel : AyahViewModel by viewModels()
    private val adapterAyah = AyahAdapter()
    private val getNomor by lazy { intent.getStringExtra("nomor") }
    private val getKeterangan by lazy { intent.getStringExtra("keterangan") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onView()
    }

    private fun onView() = binding.run {
        viewModel.getayah(getNomor!!.toInt())


        recyclerViewAyah.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterAyah
        }

        viewModel.ayah.observe(this@AyahActivity){result ->
            networkUI(result)

            result.onSuccess {ayah ->
                adapterAyah.setList(ayah)
                txtKet.text = Html.fromHtml(getKeterangan, FROM_HTML_MODE_LEGACY)
            }

        }
    }

    private fun ActivityAyahBinding.networkUI(result: ResultState<List<Ayah>>) = layoutNetwork.run {
        val isLoading = result is ResultState.Loading
        val isError = result is ResultState.Error
        btnTryAgain.setOnClickListener {
            viewModel.getayah(getNomor!!.toInt())
        }

        prNetwork.isVisible = isLoading
        btnTryAgain.isVisible = isError
        tvError.isVisible = isError

        result.onFailure { th ->
            tvError.text = th.message
        }
    }


}