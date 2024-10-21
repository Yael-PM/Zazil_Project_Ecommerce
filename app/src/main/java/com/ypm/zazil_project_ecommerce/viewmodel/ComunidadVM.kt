package com.ypm.zazil_project_ecommerce.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.PreguntasAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.RespuestasAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComunidadVM: ViewModel() {

    val controlador = ControladorServicioAPI()

    private val _listaRespuestas = MutableStateFlow<List<RespuestasAPI>>(emptyList())
    val listaRespuestas: StateFlow<List<RespuestasAPI>> = _listaRespuestas

    fun addPregunta(id_usuario: String, titulo: String, navController: NavController){
        viewModelScope.launch{
            try {
                val response = controlador.addPregunta(id_usuario, titulo)
                if(response.isSuccessful){
                    response.body()?.let {
                        Toast.makeText(navController.context, "Pregunta agregada", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e: Exception){
                Toast.makeText(navController.context, "Pregunta fallida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun obtenerListaRespuestas() {
        viewModelScope.launch {
            val listaR = controlador.obtenerListaRespuestas()
            _listaRespuestas.value = listaR
        }
    }
}