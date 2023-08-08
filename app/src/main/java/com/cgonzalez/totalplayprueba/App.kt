package com.cgonzalez.totalplayprueba

import android.app.Application
import com.cgonzalez.totalplayprueba.model.network.retrofit.ApiClient

class App : Application() {

    companion object {
        fun getApiClient() = ApiClient.buildApiService()
    }
}