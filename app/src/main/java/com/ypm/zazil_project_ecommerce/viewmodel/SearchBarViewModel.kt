package com.ypm.zazil_project_ecommerce.viewmodel

import androidx.lifecycle.ViewModel
import com.ypm.zazil_project_ecommerce.model.dataAPI.Pedido
import com.ypm.zazil_project_ecommerce.model.dataAPI.Pregunta
import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * La siguiente clase es el ViewModel del componente SearchBarComponent que ayudará
 * a realizar busqueda y gestionará el estado.
 * Este componente se manda a llamar en las interfaces de: Tienda, Historial y Foro
**/

class SearchBarViewModel: ViewModel() {
    // Variable que representa el estado del textfield sin ingresar texto
    private val _buscarTexto = MutableStateFlow("")
    val buscarTexto = _buscarTexto.asStateFlow()

    // Variable que representa el estado si se encuentra buscando algo o no
    private val _buscando = MutableStateFlow(false)
    val buscando = _buscando.asStateFlow()

    // Definimos la variable productos que guardará una lista de productos
    // cuando se consulte desde la API
    private val _productos = MutableStateFlow(listOf<ProductosAPI>())

    // Definimos la variable preguntas que guardará una lista de preguntas del foro
    // cuando se consulte desde la API
    private val _preguntas = MutableStateFlow(listOf<Pregunta>())

    // Definimos la variable pedidos que guardará una lista de pedidos
    // cuando se consulte desde la API
    private val _pedidos = MutableStateFlow(listOf<Pedido>())

}