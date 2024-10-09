package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import kotlinx.coroutines.launch

class RegistroVM: ViewModel() {

    // Variable asociada al estado del textfield de nombre
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    // Variable asociada al estado del textfield de apellido paterno
    private val _apellidoPaterno = MutableLiveData<String>()
    val apellidoPaterno: LiveData<String> = _apellidoPaterno

    // Variable asociada al estado del textfield de apellido materno
    private val _apellidoMaterno = MutableLiveData<String>()
    val apellidoMaterno: LiveData<String> = _apellidoMaterno

    // Variable asociada al estado del textfield de correo
    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo

    // Variable asociada al estado del textfield de password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Variable asociada al estado de los campos
    private val _campoError = MutableLiveData<String?>()
    val campoError: LiveData<String?> = _campoError

    // Variable asociada al estado del textfield de correo en error
    private val _correoError = MutableLiveData<String?>()
    val correoError: LiveData<String?> = _correoError

    // Variable asociada al estado del textfield de password en error
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    // Variable asociada al estado del registro
    private val _registroEnabled = MutableLiveData<Boolean>()
    val registroEnabled: LiveData<Boolean> = _registroEnabled
    val registerResponse = MutableLiveData<UsuariosAPI>()
    val registerError = MutableLiveData<String>()
    val controlador = ControladorServicioAPI()

    fun validRegister(nombre: String, apellidoPaterno: String, apellidoMaterno: String, correo: String, password: String){
        _nombre.value = nombre
        _apellidoPaterno.value = apellidoPaterno
        _apellidoMaterno.value = apellidoMaterno
        _correo.value = correo
        _password.value = password

        // Validación de campos
        if (!campoValido(nombre, apellidoPaterno, apellidoMaterno)) {
            _campoError.value = "Llena los campos, deben tener al menos 3 caracteres"
        } else {
            _campoError.value = null
        }

        // Validación de correo
        if (!correoValido(correo)) {
            _correoError.value = "Correo inválido"
        } else if (correo.isEmpty()) {
            _correoError.value = "El campo correo no puede estar vacío"
        } else {
            _correoError.value = null
        }

        // Validación de contraseña
        if (!passwordValida(password)) {
            _passwordError.value = "Contraseña debe tener al menos 6 caracteres"
        } else if (password.isEmpty()) {
            _passwordError.value = "El campo contraseña no puede estar vacío"
        } else {
            _passwordError.value = null
        }

        _registroEnabled.value = campoValido(nombre, apellidoPaterno, apellidoMaterno) && correoValido(correo) && passwordValida(password)
    }

    fun campoValido(nombre: String, apellidoPaterno: String, apellidoMaterno: String): Boolean {
        return (nombre.isNotEmpty() && nombre.length >= 3 && apellidoPaterno.isNotEmpty() && apellidoPaterno.length >= 3 && apellidoMaterno.isNotEmpty() && apellidoMaterno.length >= 3)
    }

    fun passwordValida(password: String): Boolean {
        return password.length >= 6 && password.isNotEmpty()
    }

    fun correoValido(correo: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches() && correo.isNotEmpty()
    }

    fun registroValido(nombre: String, apellidoPaterno: String, apellidoMaterno: String, correo: String, password: String) {
        viewModelScope.launch {
            try{
                val repsonse = controlador.register(nombre, apellidoPaterno, apellidoMaterno, correo, password)
                if(repsonse.isSuccessful){
                    registerResponse.value = repsonse.body()
                }else{
                    registerError.value = "Error en el registro"
                }
            } catch (e: Exception){
                registerError.value = e.message
            }
        }
    }
}