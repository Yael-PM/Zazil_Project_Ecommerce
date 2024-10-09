package com.ypm.zazil_project_ecommerce.model.dataAPI

data class ProductosAPI(
    val id_producto: Int,
    val nombre_producto: String,
    val categoria: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val ruta_img: String,
    val URL_producto: String
)

// Clase para la respuesta completa de la API
data class ApiResponse(
    val products: List<ProductosAPI>
)
