package com.recyclerview.mvvm.network


import com.google.gson.Gson
import com.recyclerview.mvvm.model.CountryResult
import com.recyclerview.mvvm.utils.SharedPreference
import retrofit2.Response


class Repository {
    val sharedPreference = SharedPreference()

    suspend fun getCountriesState(): Response<CountryResult> {
        val response = getClient().getCountriesState()
        if (response.isSuccessful) {
            sharedPreference.putString("country_state", Gson().toJson(response.body()))
        }
        return response
    }


}