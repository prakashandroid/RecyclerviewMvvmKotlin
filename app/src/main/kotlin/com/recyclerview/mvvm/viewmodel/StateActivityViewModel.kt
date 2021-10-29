package com.recyclerview.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.recyclerview.mvvm.adapter.StateAdapter
import com.recyclerview.mvvm.model.CountryItem


class StateActivityViewModel : ViewModel() {
    val stateAdapter = StateAdapter()
    var countryItem = MutableLiveData<CountryItem>()

    var title = MutableLiveData<String>("")

  init{
        countryItem.observeForever() {
            title.value=it.name
            stateAdapter.updateAdapter(it.states)
        }


    }


}
