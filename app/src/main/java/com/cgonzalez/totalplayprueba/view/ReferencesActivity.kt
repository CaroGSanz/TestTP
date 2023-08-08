package com.cgonzalez.totalplayprueba.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cgonzalez.totalplayprueba.R
import com.cgonzalez.totalplayprueba.model.repository.PreferencesRepository
import com.cgonzalez.totalplayprueba.util.Constants
import com.cgonzalez.totalplayprueba.util.Utils
import com.cgonzalez.totalplayprueba.view.adapter.ReferencesAdapter
import com.cgonzalez.totalplayprueba.viewModel.PreferencesViewModel
import com.cgonzalez.totalplayprueba.viewModel.PreferencesViewModelFactory

class ReferencesActivity : AppCompatActivity() {

    private lateinit var preferencesViewModel: PreferencesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_references)
        setUpUI()
        val userSession = intent.extras?.getString(Constants.TOKEN_KEY) ?: ""
        preferencesViewModel.getAllPreferences(userSession)

        preferencesViewModel.preferencesResponse.observe(this) {
            if (it.isSuccess) {
                val adapter = ReferencesAdapter(it.getOrNull()!!)
                rvPreferences.adapter = adapter
            } else {
                Utils.createAlertDialog(
                    this,
                    R.drawable.error,
                    "Error de servidor",
                    it.exceptionOrNull()?.message.toString()
                )
            }
        }
        preferencesViewModel.isLoading.observe(this) {
            if (it) showLoader()
            else hideLoader()
        }

    }

    private fun showLoader() {
        loader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        loader.visibility = View.GONE
    }

    private lateinit var loader: ConstraintLayout
    private lateinit var rvPreferences: RecyclerView
    private fun setUpUI() {
        val repository = PreferencesRepository()
        preferencesViewModel = ViewModelProvider(
            this,
            PreferencesViewModelFactory(repository)
        )[PreferencesViewModel::class.java]

        loader = findViewById(R.id.loader)
        rvPreferences = findViewById(R.id.rv_preferences)
        rvPreferences.layoutManager = LinearLayoutManager(this)
    }
}