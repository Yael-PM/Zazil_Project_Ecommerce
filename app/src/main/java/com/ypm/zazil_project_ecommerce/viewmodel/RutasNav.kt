package com.ypm.zazil_project_ecommerce.viewmodel

/**
 * Objeto que define las rutas de navegación dentro de la aplicación.
 * Cada ruta puede incluir parámetros dinámicos, como el ID del usuario o del producto.
 */
object RutasNav {
    const val HOME = "home/{id}"
    const val REGISTRO = "registrar"
    const val AVISO = "aviso"
    const val LOGIN = "login"
    const val COMUNIDAD = "comunidad/{id}"
    const val CONOCENOS = "conocenos/{id}"
    const val HISTORIAL = "historial/{id}"
    const val TIENDA = "tienda/{id}"
    const val PERFIL = "perfil/{id}" //Ruta para ir al perfil del usuario
    const val CARRITO = "carrito/{id}"
    const val DETALLES = "detalle/{id}" //Ruta para ir al detalle del producto
}