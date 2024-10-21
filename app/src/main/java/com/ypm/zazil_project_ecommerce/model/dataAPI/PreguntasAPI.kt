package com.ypm.zazil_project_ecommerce.model.dataAPI

data class PreguntasAPI(
    val id_usuario: String,
    val titulo: String,
)

data class RespuestasAPI(
    val id_pregunta: String,
    val id_usuario: String,
    val firstName: String,
    val lastName: String,
    val titulo: String,
    val id_usuario_respuesta: String,
    val respuesta: String,
    val fecha_respuesta: String
)

data class ListaPreguntasAPI(
    val preguntas: List<RespuestasAPI>
)
