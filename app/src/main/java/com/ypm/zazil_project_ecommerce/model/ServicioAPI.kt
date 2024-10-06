package com.ypm.zazil_project_ecommerce.model

import retrofit2.Response
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Modela el API para obtener la información de productos
 **/

interface ServicioAPI {
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
     * */
    @GET("api/productos/") //Endpoint de la API
    suspend fun obtenerDetalleProducto(@Path("id") id: Int): ProductosAPI
}