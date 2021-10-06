package com.dedi.muslimjelajah.view.activity

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.HomeLayoutBinding
import com.dedi.muslimjelajah.view.fragment.surah.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val binding: HomeLayoutBinding by viewBinding()

    private val viewModel: SurahViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onView()


    }

    private fun onView() = binding.run{

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigation, navHostFragment.navController)

        viewModel.getSurah()


    }




}