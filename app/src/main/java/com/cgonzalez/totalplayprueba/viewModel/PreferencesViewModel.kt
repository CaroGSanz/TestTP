package com.cgonzalez.totalplayprueba.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cgonzalez.totalplayprueba.model.network.response.ArrayReference
import com.cgonzalez.totalplayprueba.model.repository.PreferencesRepository
import com.cgonzalez.totalplayprueba.view.contract.PreferencesCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreferencesViewModel(private val repository: PreferencesRepository) : ViewModel() {

    val isLoading = MutableLiveData(true)
    val preferencesResponse = MutableLiveData<Result<List<ArrayReference>>>()

    fun getAllPreferences(session: String) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.getAllPreferences(session, object : PreferencesCallback {
                override fun onResponseSuccessPreferences(response: List<ArrayReference>) {
                    isLoading.value = false
                    preferencesResponse.value = Result.success(response)
                }

                override fun onResponseFailurePreferences(throwable: Throwable) {
                    isLoading.value = false
                    preferencesResponse.value = Result.failure(throwable)
                }
            })
        }
    }
}