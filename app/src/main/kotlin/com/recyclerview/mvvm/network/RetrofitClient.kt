package com.recyclerview.mvvm.network


import android.util.Log
import com.recyclerview.mvvm.utils.TAG


import kotlinx.coroutines.TimeoutCancellationException
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

private var retrofitUrl: RetrofitUrl? = null
private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val BASE_URL = "https://raw.githubusercontent.com/prakashandroid/"


fun getClient(): RetrofitUrl {
    if (retrofitUrl == null) {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .cache(null)
            .build()
        retrofitUrl = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitUrl::class.java)
    }
    return retrofitUrl!!
}

fun errorException(throwable: Throwable): String {
    var error_msg = ""
    if (throwable is SocketException) {
        error_msg = "Socket Exception"
    } else if (throwable is HttpException) {
        error_msg = "Http Exception\n" + throwable.response()?.errorBody()?.string()
    } else if (throwable is UnknownHostException) {
        error_msg = "Internet Connection Error"
    } else if (throwable is TimeoutException || throwable is TimeoutCancellationException) {
        error_msg = "Timeout Exception"
    } else if (throwable is Exception) {
        error_msg = "Unknown Error"  + throwable.localizedMessage?.toString()
    }
    Log.d(TAG, "API_Error:\n" + error_msg )
    return error_msg
}
