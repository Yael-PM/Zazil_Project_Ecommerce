package com.ypm.zazil_project_ecommerce.model.dataAPI

data class CarritoAPI(
    val id_carrito: Int,
    val id_usuario: Int,
    val id_producto: Int,
    val cantidad: Int,
    val imagen: String,
    val nombre: String,
    val precio: String,
    val stock: Int,
)
