package com.cgonzalez.totalplayprueba.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.cgonzalez.totalplayprueba.R
import com.cgonzalez.totalplayprueba.model.repository.LoginRepository
import com.cgonzalez.totalplayprueba.util.Constants
import com.cgonzalez.totalplayprueba.util.Utils
import com.cgonzalez.totalplayprueba.util.encodeBase64
import com.cgonzalez.totalplayprueba.viewModel.LoginViewModel
import com.cgonzalez.totalplayprueba.viewModel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // usuario  -  user000totalplay   en B64 para mandarlo
        // password - 1234567890  en B64 para mandarlo
        setUpUI()

        loginViewModel.isLoading.observe(this) {
            if (it) showLoader()
            else hideLoader()
        }

        loginViewModel.clientResponse.observe(this) {
            if (it.isSuccess) {
                starPreferencesActivity(it.getOrNull()!!.session)
            } else {
                Utils.createAlertDialog(
                    this,
                    R.drawable.error,
                    "Error de servidor",
                    it.exceptionOrNull()?.message.toString()
                )
            }
        }
    }

    private fun starPreferencesActivity(client: String) {
        val intent = Intent(this, ReferencesActivity::class.java)
        intent.putExtra(Constants.TOKEN_KEY, client)
        startActivity(intent)
        finish()
    }

    private fun isValidCredentials(user: String, password: String): Boolean {
        return user.isNotBlank() && password.isNotBlank()
    }

    private fun showLoader() {
        loader.visibility = View.VISIBLE
        etUser.isEnabled = false
        etPassword.isEnabled = false
        tvForgotPassword.isEnabled = false
        tvRegister.isEnabled = false
        btnLogin.isEnabled = false
    }

    private fun hideLoader() {
        loader.visibility = View.GONE
        etUser.isEnabled = true
        etPassword.isEnabled = true
        tvForgotPassword.isEnabled = true
        tvRegister.isEnabled = true
        btnLogin.isEnabled = true
    }

    private lateinit var btnLogin: Button
    private lateinit var etUser: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvRegister: TextView

    private lateinit var loader: ConstraintLayout
    private fun setUpUI() {
        val repository = LoginRepository()
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory(repository))[LoginViewModel::class.java]
        etUser = findViewById(R.id.et_User)
        etPassword = findViewById(R.id.et_Password)
        btnLogin = findViewById(R.id.btn_login)
        tvForgotPassword = findViewById(R.id.tv_forgot_password)
        tvRegister = findViewById(R.id.tv_register)
        loader = findViewById(R.id.loader)

        tvRegister.setOnClickListener {
            Toast.makeText(this, "Cambiar/Buscar la contraseña", Toast.LENGTH_LONG).show()
        }
        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Registrar nueva contraseña", Toast.LENGTH_LONG).show()
        }
        btnLogin.setOnClickListener {
            if (isValidCredentials(etUser.text.toString(), etPassword.text.toString())) {
                loginViewModel.getClient(
                    etUser.text.toString().encodeBase64(),
                    etPassword.text.toString().encodeBase64()
                )
            } else {
                Utils.createAlertDialog(
                    this,
                    R.drawable.info, "Error en el formulario", "Usuario o contraseña vacia"
                )
            }
        }
    }
}