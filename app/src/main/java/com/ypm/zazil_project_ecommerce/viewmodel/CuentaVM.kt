package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar la cuenta del usuario
class CuentaVM : ViewModel() {

    // Controlador para interactuar con el servicio de API
    private val controlador = ControladorServicioAPI()

    // Estado del usuario, inicialmente nulo
    private val _usuario = MutableStateFlow<UsuariosAPI?>(null)
    val usuario: StateFlow<UsuariosAPI?> = _usuario

    // Estado de los campos de usuario
    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    private val _apellidoPaterno = MutableStateFlow("")
    val apellidoPaterno: StateFlow<String> = _apellidoPaterno

    private val _apellidoMaterno = MutableStateFlow("")
    val apellidoMaterno: StateFlow<String> = _apellidoMaterno

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _rutaImg = MutableStateFlow("")
    val rutaImg: StateFlow<String> = _rutaImg

    /**
     * Método para obtener los datos del usuario desde el backend
     * @param id El ID del usuario a obtener
     */
    fun obtenerUsuario(id: String) {
        viewModelScope.launch {
            try {
                // Llama al controlador para obtener los datos del usuario
                val usuarioData = controlador.obtenerUsuario(id)
                _usuario.value = usuarioData

                // Asigna los valores obtenidos a los respectivos StateFlows
                usuarioData?.let {
                    _nombre.value = it.nombre
                    _apellidoPaterno.value = it.apellido_paterno
                    _apellidoMaterno.value = it.apellido_materno
                    _email.value = it.email
                    _password.value = it.password
                    _rutaImg.value = it.ruta_img
                }
            } catch (e: Exception) {
                // Captura y registra cualquier error ocurrido durante la obtención del usuario
                Log.d("Error", e.message.toString())
            }
        }
    }

    // Métodos para actualizar los valores de los campos editables
    fun actualizarNombre(nuevoNombre: String) {
        _nombre.value = nuevoNombre
    }

    fun actualizarApellidoPaterno(nuevoApellidoPaterno: String) {
        _apellidoPaterno.value = nuevoApellidoPaterno
    }

    fun actualizarApellidoMaterno(nuevoApellidoMaterno: String) {
        _apellidoMaterno.value = nuevoApellidoMaterno
    }

    fun actualizarEmail(nuevoEmail: String) {
        _email.value = nuevoEmail
    }

    fun actualizarPassword(nuevaPassword: String) {
        _password.value = nuevaPassword
    }

    fun actualizarRutaImg(nuevaRutaImg: String) {
        _rutaImg.value = nuevaRutaImg
    }

    /**
     * Método para guardar los cambios del usuario en el backend
     * @param usuarioID El ID del usuario a actualizar
     */
    fun actualizarUsuario(usuarioID: String) {
        viewModelScope.launch {
            try {
                // Crea una instancia de UsuariosAPI con los datos actualizados
                val usuarioActualizado = UsuariosAPI(
                    id_usuario = _usuario.value?.id_usuario ?: 0,
                    nombre = _nombre.value,
                    apellido_paterno = _apellidoPaterno.value,
                    apellido_materno = _apellidoMaterno.value,
                    tipo_usuario = _usuario.value?.tipo_usuario ?: "usuario_n",
                    estatus_usuario = _usuario.value?.estatus_usuario ?: "activo",
                    email = _email.value,
                    ruta_img = _rutaImg.value,
                    password = _password.value
                )
                // Llama al controlador para actualizar los datos del usuario
                val resultado = controlador.actualizarUsuario(usuarioID, usuarioActualizado)
                if (resultado.isSuccessful) {
                    Log.d("CuentaVM", "Datos del usuario actualizados correctamente")
                } else {
                    Log.e("CuentaVM", "Error al actualizar los datos del usuario: ${resultado.errorBody()}")
                }
            } catch (e: Exception) {
                // Captura y registra cualquier excepción ocurrida durante la actualización
                Log.e("CuentaVM", "Excepción al actualizar los datos del usuario", e)
            }
        }
    }
}
