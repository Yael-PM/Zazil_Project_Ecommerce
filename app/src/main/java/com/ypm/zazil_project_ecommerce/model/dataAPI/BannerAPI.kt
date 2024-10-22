package com.ypm.zazil_project_ecommerce.model.dataAPI

// Data class que representa la información de un banner proveniente de la API
data class BannesrAPI(
    val id_banner: Int,
    val nombre_banner: String,
    val ruta_banner: String
)

// Data class que representa una lista de banners
data class Banners(
    val banners: List<BannesrAPI>
)