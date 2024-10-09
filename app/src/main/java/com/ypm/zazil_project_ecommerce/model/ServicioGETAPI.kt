package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
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
    suspend fun obtenerDetalleProducto(@Path("id") id: Int): ProductosAPI

    /**
     * Obtiene el dettalle del producto con la información específicada
     * en la estructura de ProductosAPI.kt
     * @return Un producto de [ProductosAPI] que contiene la información
     * de ese producto
     * */

}