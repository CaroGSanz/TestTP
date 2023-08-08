package com.cgonzalez.totalplayprueba.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cgonzalez.totalplayprueba.model.network.response.SessionClientResponse
import com.cgonzalez.totalplayprueba.model.repository.LoginRepository
import com.cgonzalez.totalplayprueba.view.contract.LoginCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val isLoading = MutableLiveData(false)
    val clientResponse = MutableLiveData<Result<SessionClientResponse>>()

    fun getClient(user: String, password: String) {
        isLoading.value = true
        CoroutineScope(Dispatchers.Main).launch {
            repository.getSessionToken(user, password, object : LoginCallback {
                override fun onResponseSuccessLogin(response: SessionClientResponse) {
                    isLoading.value = false
                    clientResponse.value = Result.success(response)
                }

                override fun onResponseFailureLogin(throwable: Throwable) {
                    isLoading.value = false
                    clientResponse.value = Result.failure(throwable)
                }
            })
        }
    }
}