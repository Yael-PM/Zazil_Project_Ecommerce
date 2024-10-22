package com.ypm.zazil_project_ecommerce.model.dataAPI

// Data class que representa la adición de un carrito nuevo
data class AddCarritoAPI(
    val id_usuario: String,
    val id_producto: String,
    val cantidad: String,
)

// Data class que representa la info de un carrito proveniente de la API
data class CarritoAPI(
    val id_carrito: Int,
    val estado: String,
    val id_producto_carrito: Int,
    val id_producto: Int,
    val cantidad: Int,
    val nombre_producto: String,
    val precio: Double,
    val ruta_img: String,
    val rating: Double,
    val stock: Int,
    val id_usuario: Int,

)

// Data class que representa una actualización del estado de un carrito
data class ActualizarCarritoAPI(
    val id_carrito: String,
    val id_producto_carrito: String,
    val cantidad: Int // Campo para la cantidad
)
