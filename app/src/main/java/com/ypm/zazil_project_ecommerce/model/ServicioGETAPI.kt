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
 * Modela el API para obtener la información de productos
 **/

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


    @GET("api/carrito/{id}") //Endpoint de la API"
    suspend fun obtenerCarrito(@Path("id") id: String): List<CarritoAPI>

    @GET("api/banners")
    suspend fun obtenerBanners(): List<BannesrAPI>

    @GET("api/carrito/{id_usuario}/completo")
    suspend fun carritoCompletado(@Path("id_usuario") id_usuario: String): Boolean

    @GET("api/carrito/{id_usuario}/completoNP")
    suspend fun obtenerHistorial(@Path ("id_usuario") id_usuario: String): List<HistorialAPI>

    @GET("api/preguntas")
    suspend fun obtenerRespuesta(): List<RespuestasAPI>

}