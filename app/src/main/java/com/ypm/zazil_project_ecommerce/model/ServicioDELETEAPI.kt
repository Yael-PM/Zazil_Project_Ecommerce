package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Servicio para realizar operaciones DELETE en la API relacionadas con el carrito de compras.
 * Esta interfaz define la función para eliminar un producto del carrito utilizando el método HTTP DELETE.
 */
interface ServicioDELETEAPI {

    /**
     * Elimina un producto del carrito de compras.
     * Esta función realiza una petición DELETE a la API para eliminar un producto específico de un carrito de compras.
     * @param id_carrito El identificador único del carrito de compras.
     * @param id_producto_carrito El identificador único del producto dentro del carrito que se desea eliminar.
     * @return Una respuesta de tipo `Response<Void>` que indica el resultado de la operación.
     */
    @DELETE("api/carrito/{id_carrito}/{id_producto_carrito}") // Endpoint de la API para eliminar un producto del carrito
    suspend fun borrarProductoCarrito(
        @Path("id_carrito") id_carrito: String,
        @Path("id_producto_carrito") id_producto_carrito: String
    ): Response<Void>
}