package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CuentaVM: ViewModel() {

    private val controlador = ControladorServicioAPI()

    // Variable que recupera la lista de datos del usuario
    private val _usuario = MutableLiveData<UsuariosAPI?>()
    val usuario: LiveData<UsuariosAPI?> = _usuario

    // Variable que recupera el nombre del usuario
    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    // Variable que recupera el apellido paterno del usuario
    private val _apellidoPaterno = MutableStateFlow("")
    val apellidoPaterno: StateFlow<String> = _apellidoPaterno

    // Variable que recupera el apellido materno del usuario
    private val _apellidoMaterno = MutableStateFlow("")
    val apellidoMaterno: StateFlow<String> = _apellidoMaterno

    // Variable que recupera el email del usuario
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    // Variable que recupera la contraseña del usuario
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    // Variable que recupera la fecha de nacimiento del usuario
    private val _fechaNacimiento = MutableStateFlow("")
    val fechaNacimiento: StateFlow<String> = _fechaNacimiento

    // Variable que recupera la fecha de nacimiento del usuario
    private val _rutaImg = MutableStateFlow("")
    val rutaImg: StateFlow<String> = _rutaImg

    // Variable de error de campo
    private val _errorNombre = MutableStateFlow("")
    val errorNombre: StateFlow<String> = _errorNombre

    // Variable de error de campo
    private val _errorApellidoPaterno = MutableStateFlow("")
    val errorApellidoPaterno: StateFlow<String> = _errorApellidoPaterno

    // Variable de error de campo
    private val _errorApellidoMaterno = MutableStateFlow("")
    val errorApellidoMaterno: StateFlow<String> = _errorApellidoMaterno

    // Variable de error de emal del usuario
    private val _errorEmail = MutableStateFlow("")
    val errorEmail: StateFlow<String> = _errorEmail

    // Variable de error de contraseña del usuario
    private val _errorPassword = MutableStateFlow("")
    val errorPassword: StateFlow<String> = _errorPassword

    // Variable de error de fecha de nacimiento del usuario
    private val _errorFechaNacimiento = MutableStateFlow("")
    val errorFechaNacimiento: StateFlow<String> = _errorFechaNacimiento

    fun obtenerUsuario(id: String) {
        viewModelScope.launch {
            try{
                _usuario.value = controlador.obtenerUsuario(id)
            }catch (e: Exception){
                Log.d("Error", e.message.toString())
            }
        }
        Log.d("CuentaVM", "Datos descargados")
        Log.d("CuentaVM", _usuario.value.toString())
    }

}