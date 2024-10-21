package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface ServicioDELETEAPI {

    @DELETE("api/carrito/{id_carrito}/{id_producto_carrito}") // Endpoint de la API para eliminar un producto del carrito
    suspend fun borrarProductoCarrito(
        @Path("id_carrito") id_carrito: String,
        @Path("id_producto_carrito") id_producto_carrito: String
    ): Response<Void>
}