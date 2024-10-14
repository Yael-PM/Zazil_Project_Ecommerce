package com.ypm.zazil_project_ecommerce.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoVM {

    // Variable que corresponde al estado del botón de carrito en el CardStoreComponent
    private val _estadoCarrito = MutableStateFlow(false)
    val estadoCarrito: StateFlow<Boolean> = _estadoCarrito





}