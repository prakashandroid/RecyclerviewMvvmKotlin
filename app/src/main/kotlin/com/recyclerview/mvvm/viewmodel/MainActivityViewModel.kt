package com.recyclerview.mvvm.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.recyclerview.mvvm.adapter.CountryAdapter
import com.recyclerview.mvvm.model.CountryItem
import com.recyclerview.mvvm.model.CountryResult
import com.recyclerview.mvvm.network.Repository
import com.recyclerview.mvvm.network.errorException
import com.recyclerview.mvvm.utils.CountryItemClickListener
import com.recyclerview.mvvm.utils.SharedPreference
import com.recyclerview.mvvm.view.StateActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel(), CountryItemClickListener {
    val repository = Repository()
    val sharedPreference = SharedPreference()
    val countryAdapter = CountryAdapter()
    var isLoaderVisible = MutableLiveData<Boolean>(false)
    var countryResult = MutableLiveData<CountryResult>()
    var errorMessage = MutableLiveData<String>("")

    init {
        val json = sharedPreference.getString("country_state")
        if (json!!.isNotEmpty())
            countryResult.value = (Gson().fromJson(json, CountryResult::class.java))


        countryResult.observeForever {
            countryAdapter.updateAdapter(it)
        }

        countryAdapter.setClickItemListener(this)
        getCountriesState()
    }

    fun getCountriesState() {
        isLoaderVisible.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    val response = repository.getCountriesState()
                    countryResult.postValue(response.body())
                }
            }.onSuccess {
                isLoaderVisible.value = false

            }.onFailure {
                isLoaderVisible.value = false
                errorMessage.value = errorException(it)
            }
        }
    }

    override fun onItemClick(view: View, item: CountryItem) {
        val intent = Intent(view.context, StateActivity::class.java)
        intent.putExtra("countryItem", item)
        view.context.startActivity(intent)
    }

}
