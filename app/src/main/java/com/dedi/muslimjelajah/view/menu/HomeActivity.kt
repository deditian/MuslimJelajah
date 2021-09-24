package com.dedi.muslimjelajah.view.menu

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.HomeLayoutBinding
import com.dedi.muslimjelajah.model.MenuItem
import com.dedi.muslimjelajah.model.MenuPage
import java.io.IOException
import java.nio.charset.Charset
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    // Lazy Inject ViewModel
    private val viewModel: MenuViewModel by viewModel()

    private val menuRecyclerView: MenuAdapter by lazy {
        MenuAdapter(this)
    }

    private lateinit var binding: HomeLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = HomeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.recyclerView.layoutManager = GridLayoutManager(this,  5)
        binding.recyclerView.layoutManager =  LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)

        getMenus()
        viewModel.menusLocalLiveData.observe(this, menuObserver)
        binding.recyclerView.adapter = menuRecyclerView

    }

    //observers
    private val menuObserver = Observer<MutableList<MenuItem>> {
        Log.e(TAG, "menuObserver : $it")
        menuRecyclerView.setList(it)
    }

    private fun getMenus(){
        viewModel.getMenuPages()
    }
}