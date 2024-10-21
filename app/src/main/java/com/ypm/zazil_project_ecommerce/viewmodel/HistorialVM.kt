package com.ypm.zazil_project_ecommerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.HistorialAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistorialVM: ViewModel() {

    val controlador = ControladorServicioAPI()

    private val _listaHistorial = MutableStateFlow<List<HistorialAPI>>(emptyList())
    val listaHistorial: StateFlow<List<HistorialAPI>> = _listaHistorial

    fun obtenerListaHistorial(id_usuario: String) {
        viewModelScope.launch {
            val listaH = controlador.obtenerHistorial(id_usuario)
            _listaHistorial.value = listaH
        }
    }
}