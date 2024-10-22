package com.ypm.zazil_project_ecommerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.HistorialAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar el historial de un usuario
class HistorialVM : ViewModel() {

    // Controlador para interactuar con el servicio de API
    val controlador = ControladorServicioAPI()

    // Variable para almacenar la lista del historial del usuario
    private val _listaHistorial = MutableStateFlow<List<HistorialAPI>>(emptyList())
    val listaHistorial: StateFlow<List<HistorialAPI>> = _listaHistorial // Exposición de la lista como StateFlow

    /**
     * Método para obtener el historial del usuario a través del controlador de API
     *
     * @param id_usuario El ID del usuario cuyo historial se desea obtener
     */
    fun obtenerListaHistorial(id_usuario: String) {
        viewModelScope.launch {
            // Llama al controlador para obtener el historial del usuario
            val listaH = controlador.obtenerHistorial(id_usuario)
            _listaHistorial.value = listaH // Actualiza el estado con la lista obtenida
        }
    }
}
