package com.dedi.muslimjelajah.view.fragment.kitab.juz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.FragmentJuzBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JuzFragment : Fragment(R.layout.fragment_juz) {

    private val binding : FragmentJuzBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onView()
    }

    private fun onView() = binding.run{
        textView3.text = "asd"
    }
}