package com.dedi.muslimjelajah.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.muslimjelajah.R
import com.dedi.muslimjelajah.databinding.FragmentHomeBinding
import com.dedi.muslimjelajah.databinding.FragmentSurahBinding
import com.dedi.muslimjelajah.domain.Resource
import com.dedi.muslimjelajah.domain.ResultState
import com.dedi.muslimjelajah.domain.entity.MenuItem
import com.dedi.muslimjelajah.domain.entity.NewsArticle
import com.dedi.muslimjelajah.domain.entity.Surah
import com.dedi.muslimjelajah.utils.onFailure
import com.dedi.muslimjelajah.view.fragment.home.news.NewsAdapter
import com.dedi.muslimjelajah.view.fragment.home.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding  by viewBinding()
    private val viewModel: HomeViewModel by activityViewModels()
    private val viewModelNews: NewsViewModel by viewModels()
    private lateinit var menuAdapter: HomeAdapter
    private lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onView()
    }

    private fun onView() = binding.run {

       menuAdapter =  HomeAdapter(context)
       newsAdapter =  NewsAdapter()

        recyclerViewHome.apply {
            val mLayoutManager = GridLayoutManager(context, 5)
            recyclerViewHome.layoutManager = mLayoutManager
            recyclerViewHome.adapter = menuAdapter
        }

        recyclerViewNews.apply {
            recyclerViewNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            recyclerViewNews.adapter = newsAdapter
        }

        viewModel.menusLocalLiveData.observe(viewLifecycleOwner, menuObserver)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelNews.breakingNews.collect {
                val result = it ?: return@collect
                pbHome.isVisible = result is Resource.Loading
                recyclerViewNews.isVisible = !result.data.isNullOrEmpty()


                Log.e("TAG", "onView NEWS: ${result.data}", )
                networkUI(result)

                result.data?.let { data -> newsAdapter.setList(data) }
            }
        }

        imageView2.setOnClickListener {
            findNavController().navigate(R.id.action_global_qiblatFragment)
        }
    }

    private fun FragmentHomeBinding.networkUI(result: Resource<List<NewsArticle>>) = layoutNetworkHome.run {
        val isLoading = !result.data.isNullOrEmpty()
        val isError = result is Resource.Error
        btnTryAgain.setOnClickListener {
            viewModelNews.onStart()
        }

        prNetwork.isVisible = isLoading
        btnTryAgain.isVisible = isError
        tvError.isVisible = isError

        tvError.text = result.error?.localizedMessage

    }

    override fun onStart() {
        super.onStart()
        viewModelNews.onStart()
    }

    private val menuObserver = Observer<List<MenuItem>> {
        Log.e("TAG", "menuObserver : $it")
        menuAdapter.setList(it)
    }
}