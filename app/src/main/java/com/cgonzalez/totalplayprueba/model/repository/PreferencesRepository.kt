package com.cgonzalez.totalplayprueba.model.repository

import com.cgonzalez.totalplayprueba.App
import com.cgonzalez.totalplayprueba.view.contract.PreferencesCallback

class PreferencesRepository {

    suspend fun getAllPreferences(tokenSession: String, callback: PreferencesCallback) {
        val preferences = App.getApiClient().getAllReferences(tokenSession)
        if (preferences.isSuccessful) {
            when (preferences.code()) {
                200 -> {
                    callback.onResponseSuccessPreferences(preferences.body()!!.arrayReferences)
                }
            }
        } else {
            callback.onResponseFailurePreferences(Throwable(preferences.message()))
        }
    }
}