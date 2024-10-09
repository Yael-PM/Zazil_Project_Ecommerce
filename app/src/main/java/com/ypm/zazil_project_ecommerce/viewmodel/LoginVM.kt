package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.ServicioPOSTAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import kotlinx.coroutines.launch

class LoginVM: ViewModel() {

    // Variable asociada al estado del textfield de correo
    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo

    // Variable asociada al estado del textfield de password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Variable asociada al estado del login
    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: LiveData<Boolean> = _loginEnabled

    // Variable asociada al estado del textfield de correo en error
    private val _correoError = MutableLiveData<String?>()
    val correoError: LiveData<String?> = _correoError

    // Variable asociada al estado del textfield de password en error
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    val loginResponse = MutableLiveData<LoginResponse>()
    val loginError = MutableLiveData<String>()
    val controlador = ControladorServicioAPI()

    fun cambioLogin(correo: String, password: String){
        _correo.value = correo
        _password.value = password
        val loginRequest = LoginRequest(correo, password)

        // Validación de correo
        if (!isValidEmail(correo)) {
            _correoError.value = "Correo inválido"
        } else if (correo.isEmpty()) {
            _correoError.value = "El campo correo no puede estar vacío"
        } else {
            _correoError.value = null
        }

        // Validación de contraseña
        if (!isValidPassword(password)) {
            _passwordError.value = "Contraseña debe tener al menos 6 caracteres"
        } else if (password.isEmpty()) {
            _passwordError.value = "El campo contraseña no puede estar vacío"
        } else {
            _passwordError.value = null
        }

        _loginEnabled.value = isValidEmail(correo) && isValidPassword(password)
    }

    // Valida que el email tenga un formato correcto
    private fun isValidEmail(correo: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches() && correo.isNotEmpty()
    }

    // Valida que la contraseña tenga al menos 6 caracteres
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6 && password.isNotEmpty()
    }

    fun validarLogin(correo: String, password: String, navController: NavController){
        viewModelScope.launch {
            try{
                val response = controlador.login(correo, password)
                if(response.isSuccessful){
                    response.body()?.let{
                        loginResponse.value = it
                        navController.navigate(RutasNav.HOME)
                        Toast.makeText(navController.context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        if (!it.estatus){
                            loginError.value = it.estatus.toString()
                        }
                    }
                }else{
                    loginError.value = "Error en el inicio de sesión"
                    Toast.makeText(navController.context, "Inicio de sesión inválido", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){
                loginError.value = e.message
            }
        }
    }
}