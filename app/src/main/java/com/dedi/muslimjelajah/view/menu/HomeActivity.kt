package com.dedi.muslimjelajah.view.menu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.dedi.muslimjelajah.databinding.HomeLayoutBinding
import com.dedi.muslimjelajah.model.MenuItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        val mLayoutManager = GridLayoutManager(this, 5)
        mLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (menuRecyclerView.getItemViewType(position)) {
                    MenuAdapter.ITEM_HEADER -> 5
                    MenuAdapter.ITEM_MENU -> 1
                    else -> 1
                }
            }
        }
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.adapter = menuRecyclerView

        viewModel.getMenuPagesLiveData().observe(this@HomeActivity, menuObserver)

    }

    //observers
    private val menuObserver = Observer<List<MenuItem>> {
        Log.e(TAG, "menuObserver : $it")
        if (it != null){
            menuRecyclerView.setList(it)
        }

    }


}