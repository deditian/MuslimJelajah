package com.dedi.muslimjelajah.view.activity.ayah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.FragmentAyahBinding
import com.dedi.muslimjelajah.databinding.FragmentSurahBinding
import com.dedi.muslimjelajah.databinding.ItemAyahBinding
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.Ayah
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.DATA_SURAH
import com.dedi.muslimjelajah.utils.onFailure
import com.dedi.muslimjelajah.utils.onSuccess
import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class AyahActivity : Fragment(R.layout.fragment_ayah) {
//
//    private val binding : FragmentAyahBinding by viewBinding()
//    private val viewModel : AyahViewModel by activityViewModels()
//    private val adapterAyah = AyahAdapter()
//    private val data by lazy {  intent.getParcelableExtra<Surah>(DATA_SURAH) }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        onView()
//    }
//
//    private fun onView() = binding.run {
//        viewModel.getayah(data!!.nomor.toInt())
//
//        recyclerViewAyah.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = adapterAyah
//        }
//
//        networkHeader(data!!)
//
//        viewModel.ayah.observe(viewLifecycleOwner){result ->
//            networkUI(result)
//            val isSuccess = result is ResultState.Success
//            txtKet.isVisible = isSuccess
//            result.onSuccess {ayah ->
//                adapterAyah.setList(ayah)
//                txtKet.text = Html.fromHtml(data?.keterangan, FROM_HTML_MODE_LEGACY)
//            }
//        }
//    }
//
//    private fun FragmentAyahBinding.networkHeader(item: Surah) = layoutItemSurah.run {
//        txtAsma.text = item.asma
//        txtAyat.text = item.ayat
//        txtNama.text = item.nama
//        txtNumber.text = item.nomor
//        txtType.text = item.type
//
//    }
//
//    private fun FragmentAyahBinding.networkUI(result: ResultState<List<Ayah>>) = layoutNetwork.run {
//        val isLoading = result is ResultState.Loading
//        val isError = result is ResultState.Error
//        btnTryAgain.setOnClickListener {
//            viewModel.getayah(data!!.nomor.toInt())
//        }
//
//
//        prNetwork.isVisible = isLoading
//        btnTryAgain.isVisible = isError
//        tvError.isVisible = isError
//
//        result.onFailure { th ->
//            tvError.text = th.message
//        }
//    }



//}