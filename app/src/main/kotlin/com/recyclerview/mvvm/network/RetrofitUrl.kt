package com.recyclerview.mvvm.network

import com.recyclerview.mvvm.model.CountryResult
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitUrl {

    //URL =https://raw.githubusercontent.com/prakashandroid/RecyclerviewMvvmKotlin/main/country_state.josn
    @GET("RecyclerviewMvvmKotlin/main/country_state.josn")
    suspend fun getCountriesState(): Response<CountryResult>

}