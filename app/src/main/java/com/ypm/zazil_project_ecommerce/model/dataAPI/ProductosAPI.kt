package com.ypm.zazil_project_ecommerce.model.dataAPI

// Data class que representa un producto
data class ProductosAPI(
    val id_producto: Int = 0,
    val nombre_producto: String = "",
    val categoria: String= "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0,
    val ruta_img: String = "",
    val rating: Float = 0.0F,
)

// Clase para la respuesta completa de la API
data class ApiResponse(
    val products: List<ProductosAPI>
)
