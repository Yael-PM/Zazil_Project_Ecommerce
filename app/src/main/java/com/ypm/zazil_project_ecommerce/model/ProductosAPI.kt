package com.ypm.zazil_project_ecommerce.model

data class ProductosAPI(
    val asin: String,
    val product_title: String,
    val product_price: String,
    val product_star_rating: String,
    val product_url: String,
    val product_photo: String,
)

// Clase para la respuesta completa de la API
data class ApiResponse(
    val products: List<ProductosAPI>
)
