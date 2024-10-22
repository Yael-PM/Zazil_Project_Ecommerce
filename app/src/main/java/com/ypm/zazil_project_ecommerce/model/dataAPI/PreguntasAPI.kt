package com.ypm.zazil_project_ecommerce.model.dataAPI

// Data class que crea una pregunta
data class PreguntasAPI(
    val id_usuario: String,
    val titulo: String,
)

// Data class que representa la respuesta en el foro
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

// Data class que representa la lista respuestas en el foro
data class ListaPreguntasAPI(
    val preguntas: List<RespuestasAPI>
)
