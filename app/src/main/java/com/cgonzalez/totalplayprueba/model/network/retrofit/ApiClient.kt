package com.cgonzalez.totalplayprueba.model.network.retrofit

import com.cgonzalez.totalplayprueba.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    fun buildApiService(): ApiService =
        getRetrofitClient().create(ApiService::class.java)
}