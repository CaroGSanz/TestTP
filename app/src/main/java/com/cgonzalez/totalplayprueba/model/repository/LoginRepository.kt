package com.cgonzalez.totalplayprueba.model.repository

import com.cgonzalez.totalplayprueba.App
import com.cgonzalez.totalplayprueba.view.contract.LoginCallback

class LoginRepository {

    suspend fun getSessionToken(user: String, password: String, callback: LoginCallback) {
        val userClient = App.getApiClient().getUserClient(user, password)
        if (userClient.isSuccessful) {
            when (userClient.code()) {
                200 -> {
                    callback.onResponseSuccessLogin(userClient.body()!!)
                }
            }
        } else {
            callback.onResponseFailureLogin(Throwable(userClient.message()))
        }
    }
}