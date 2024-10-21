package com.ypm.zazil_project_ecommerce.model.dataAPI

data class BannesrAPI(
    val id_banner: Int,
    val nombre_banner: String,
    val ruta_banner: String
)

data class Banners(
    val banners: List<BannesrAPI>
)