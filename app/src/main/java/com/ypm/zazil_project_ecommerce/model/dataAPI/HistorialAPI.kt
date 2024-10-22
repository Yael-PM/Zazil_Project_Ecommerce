package com.ypm.zazil_project_ecommerce.model.dataAPI

// Data class que representa una compra dentro de la aplicación
data class HistorialAPI(
    val id_carrito: String,
    val estado: String,
    val total: Double,
    val f_actualizacion: String,
)
