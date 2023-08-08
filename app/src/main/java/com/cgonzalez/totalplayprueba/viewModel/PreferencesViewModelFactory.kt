package com.cgonzalez.totalplayprueba.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cgonzalez.totalplayprueba.model.repository.PreferencesRepository

class PreferencesViewModelFactory(
    private val repository: PreferencesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PreferencesViewModel(repository) as T
    }
}