package com.cgonzalez.totalplayprueba.view.contract

import com.cgonzalez.totalplayprueba.model.network.response.SessionClientResponse

interface LoginCallback {
    fun onResponseSuccessLogin(response: SessionClientResponse)
    fun onResponseFailureLogin(throwable: Throwable)
}