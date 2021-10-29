package com.recyclerview.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.recyclerview.mvvm.R
import com.recyclerview.mvvm.databinding.MainActivityBinding
import com.recyclerview.mvvm.utils.showToastShort
import com.recyclerview.mvvm.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        viewModel.isLoaderVisible.observeForever {
            swipeRefreshLayout.isRefreshing = it
        }
        viewModel.errorMessage.observeForever {
            if (it.isNotEmpty())
                showToastShort(it)
        }
        swipeRefreshLayout.setOnRefreshListener { viewModel.getCountriesState() }
    }
}