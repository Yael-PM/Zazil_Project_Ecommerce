package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import kotlinx.coroutines.launch

// ViewModel para gestionar el estado y la lógica del proceso de login
class LoginVM : ViewModel() {

    // Variable para almacenar el valor ingresado en el campo de correo
    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo

    // Variable para almacenar el valor ingresado en el campo de contraseña
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Variable para controlar si el botón de login debe estar habilitado o no
    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: LiveData<Boolean> = _loginEnabled

    // Variable para almacenar el mensaje de error asociado al campo de correo (si existe)
    private val _correoError = MutableLiveData<String?>()
    val correoError: LiveData<String?> = _correoError

    // Variable para almacenar el mensaje de error asociado al campo de contraseña (si existe)
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    // Respuesta del login después de hacer la petición a la API
    val loginResponse = MutableLiveData<LoginResponse>()

    // Variable para almacenar cualquier error ocurrido durante el proceso de login
    val loginError = MutableLiveData<String>()

    // Controlador para interactuar con el servicio de API
    val controlador = ControladorServicioAPI()

    /**
     * Método para actualizar los campos de correo y contraseña, y validar si el login es posible
     * Valida el formato del correo y la contraseña antes de habilitar el botón de login
     */
    fun cambioLogin(correo: String, password: String) {
        _correo.value = correo
        _password.value = password
        val loginRequest = LoginRequest(correo, password) // Crea una instancia de LoginRequest con los valores proporcionados

        // Validación del correo
        if (!isValidEmail(correo)) {
            _correoError.value = "Correo inválido" // Si el correo no es válido, muestra un error
        } else if (correo.isEmpty()) {
            _correoError.value = "El campo correo no puede estar vacío" // Si está vacío, muestra un error
        } else {
            _correoError.value = null // No hay errores
        }

        // Validación de la contraseña
        if (!isValidPassword(password)) {
            _passwordError.value = "Contraseña debe tener al menos 6 caracteres" // Si la contraseña no es válida, muestra un error
        } else if (password.isEmpty()) {
            _passwordError.value = "El campo contraseña no puede estar vacío" // Si está vacío, muestra un error
        } else {
            _passwordError.value = null // No hay errores
        }

        // Habilita o deshabilita el botón de login según si los campos son válidos
        _loginEnabled.value = isValidEmail(correo) && isValidPassword(password)
    }

    /**
     * Valida si el correo ingresado tiene un formato correcto
     * Utiliza el patrón de direcciones de email y verifica que no esté vacío
     */
    private fun isValidEmail(correo: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches() && correo.isNotEmpty()
    }

    /**
     * Valida si la contraseña cumple con los requisitos mínimos
     * En este caso, debe tener al menos 6 caracteres y no estar vacía
     */
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6 && password.isNotEmpty()
    }

    /**
     * Método que realiza la validación del login llamando al controlador y actualizando la respuesta
     * Si el login es exitoso, navega a la pantalla de inicio
     *
     * @param correo El correo ingresado por el usuario
     * @param password La contraseña ingresada por el usuario
     * @param navController El controlador de navegación para cambiar de pantalla tras el login
     */
    fun validarLogin(
        correo: String,
        password: String,
        navController: NavController,
    ) {
        viewModelScope.launch {
            try {
                // Realiza la llamada a la API para verificar el login
                val response = controlador.login(correo, password)

                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, actualiza el estado de loginResponse y navega a la pantalla de inicio
                    response.body()?.let {
                        loginResponse.value = it

                        // Navega a la pantalla "home" pasando el id del usuario
                        navController.navigate("home/${it.id_usuario}")
                        Toast.makeText(navController.context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                        // Si el estado del login no es válido, almacena el error
                        if (!it.status_login) {
                            loginError.value = it.status_login.toString()
                        }
                    }
                } else {
                    // Si la respuesta no es exitosa, muestra un error
                    loginError.value = "Error en el inicio de sesión"
                    Toast.makeText(navController.context, "Inicio de sesión inválido", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Si ocurre una excepción, almacena el mensaje de error
                loginError.value = e.message
            }
        }
    }
}
