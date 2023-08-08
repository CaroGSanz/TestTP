package com.cgonzalez.totalplayprueba.view.contract

import com.cgonzalez.totalplayprueba.model.network.response.ArrayReference

interface PreferencesCallback {
    fun onResponseSuccessPreferences(response: List<ArrayReference>)
    fun onResponseFailurePreferences(throwable: Throwable)
}