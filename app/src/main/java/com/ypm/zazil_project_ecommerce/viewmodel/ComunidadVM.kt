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

// ViewModel para gestionar la comunidad, incluyendo preguntas y respuestas
class ComunidadVM: ViewModel() {

    // Controlador para interactuar con el servicio de API
    val controlador = ControladorServicioAPI()

    // Estado de la lista de respuestas
    private val _listaRespuestas = MutableStateFlow<List<RespuestasAPI>>(emptyList())
    val listaRespuestas: StateFlow<List<RespuestasAPI>> = _listaRespuestas

    /**
     * Método para agregar una nueva pregunta a la comunidad
     *
     * @param id_usuario El ID del usuario que está agregando la pregunta
     * @param titulo El título de la pregunta
     * @param navController Controlador de navegación para mostrar mensajes
     */
    fun addPregunta(id_usuario: String, titulo: String, navController: NavController) {
        viewModelScope.launch {
            try {
                // Llama al controlador para agregar la pregunta
                val response = controlador.addPregunta(id_usuario, titulo)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // Muestra un mensaje de éxito si la pregunta se agrega correctamente
                        Toast.makeText(navController.context, "Pregunta agregada", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Muestra un mensaje de error si falla la adición de la pregunta
                Toast.makeText(navController.context, "Pregunta fallida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Método para obtener la lista de respuestas de la comunidad
     */
    fun obtenerListaRespuestas() {
        viewModelScope.launch {
            // Llama al controlador para obtener la lista de respuestas
            val listaR = controlador.obtenerListaRespuestas()
            // Actualiza el estado de la lista de respuestas
            _listaRespuestas.value = listaR
        }
    }
}
