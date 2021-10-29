package com.recyclerview.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.recyclerview.mvvm.R

import com.recyclerview.mvvm.databinding.StateActivityBinding
import com.recyclerview.mvvm.model.CountryItem
import com.recyclerview.mvvm.model.StateItem

import com.recyclerview.mvvm.viewmodel.StateActivityViewModel
import kotlinx.android.synthetic.main.state_activity.*


class StateActivity : AppCompatActivity() {
    lateinit var binding: StateActivityBinding
    lateinit var viewModel: StateActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.state_activity)
        viewModel = ViewModelProvider(this).get(StateActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()


        val countryItem = intent.getSerializableExtra("countryItem") as CountryItem
        viewModel.countryItem.value = countryItem
        back.setOnClickListener { onBackPressed() }
    }
}