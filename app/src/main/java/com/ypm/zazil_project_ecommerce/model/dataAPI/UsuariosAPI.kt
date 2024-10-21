package com.ypm.zazil_project_ecommerce.model.dataAPI

// Modelo de datos que nos regresa la API del usuario
data class UsuariosAPI(
    val id_usuario: Int,
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val tipo_usuario: String,
    val estatus_usuario: String,
    val email: String,
    val ruta_img: String,
    val password: String
)

// Modelo de datos con método POST para verificar usuario
data class LoginRequest(
    val email: String,
    val password: String
)

// Modelo de datos que verifica al usuario
data class LoginResponse(
    val status_login: Boolean = false,
    val id_usuario: Int = 0,
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val tipo_usuario: String,
    val estatus_usuario: String,
    val email: String,
    val ruta_img: String,
    val password: String
)