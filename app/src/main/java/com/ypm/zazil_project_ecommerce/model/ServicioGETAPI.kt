package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.BannesrAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.CarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.HistorialAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.RespuestasAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Servicio para realizar operaciones GET en la API relacionadas con el carrito de compras, banners y preguntas.
 * Esta interfaz define varias funciones para obtener datos del carrito de compras, banners, historial de compras y respuestas de preguntas.
 */
interface ServicioGETAPI {
    /**
     * Obtiene la lista de productos con la información específicada
     * en la estructura de ProductosAPI.kt
     * @return Una lista de [ProductosAPI] que contiene la información
     * de cada producto
     * */
    @GET("api/productos") //Endpoint de la API
    suspend fun obtenerListaProductos(): List<ProductosAPI>

    /**
     * Obtiene el dettalle del producto con la información específicada
     * en la estructura de ProductosAPI.kt
     * @return Un producto de [ProductosAPI] que contiene la información
     * de ese producto
     **/
    @GET("api/productos/{id}") //Endpoint de la API
    suspend fun obtenerProducto(@Path("id") id: String): ProductosAPI

    /**
     * Obtiene la infromación del usuario en la estructura de UsuariosAPI.kt
     * @return Un usuario de tipo [UsuariosAPI]
     **/
    @GET("api/usuario/{id}") //Endpoint de la API
    suspend fun obtenerUsuario(@Path("id") id: String): UsuariosAPI

    /**
     * Obtiene el contenido del carrito de compras por su identificador.
     * Esta función realiza una petición GET a la API para obtener los productos dentro de un carrito específico.
     * @param id El identificador único del carrito de compras.
     * @return Una lista de objetos de tipo `CarritoAPI` que representan los productos dentro del carrito.
     */
    @GET("api/carrito/{id}")
    suspend fun obtenerCarrito(@Path("id") id: String): List<CarritoAPI>

    /**
     * Obtiene una lista de banners.
     * Esta función realiza una petición GET a la API para obtener todos los banners disponibles.
     * @return Una lista de objetos de tipo `BannesrAPI` que representan los banners.
     */
    @GET("api/banners")
    suspend fun obtenerBanners(): List<BannesrAPI>

    /**
     * Verifica si el carrito de compras ha sido completado por un usuario.
     * Esta función realiza una petición GET a la API para comprobar si el carrito del usuario ha sido completado.
     * @param id_usuario El identificador único del usuario.
     * @return Un valor booleano que indica si el carrito ha sido completado.
     */
    @GET("api/carrito/{id_usuario}/completo")
    suspend fun carritoCompletado(@Path("id_usuario") id_usuario: String): Boolean

    /**
     * Obtiene el historial de compras del usuario.
     * Esta función realiza una petición GET a la API para obtener el historial de compras de un usuario.
     * @param id_usuario El identificador único del usuario.
     * @return Una lista de objetos de tipo `HistorialAPI` que representan el historial de compras del usuario.
     */
    @GET("api/carrito/{id_usuario}/completoNP")
    suspend fun obtenerHistorial(@Path("id_usuario") id_usuario: String): List<HistorialAPI>

    /**
     * Obtiene una lista de respuestas a las preguntas realizadas.
     * Esta función realiza una petición GET a la API para obtener las respuestas disponibles a las preguntas.
     * @return Una lista de objetos de tipo `RespuestasAPI` que representan las respuestas.
     */
    @GET("api/preguntas")
    suspend fun obtenerRespuesta(): List<RespuestasAPI>
}