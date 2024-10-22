package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.ActualizarCarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.CarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Manda una petición PUT a la API para actualizar ciertos datos
 **/
interface ServicioPUTAPI {

    /**
     * Manda una petición PUT a la API para actualizar ciertos datos del usuario
     * @param id: El id del usuario que deseas actualizar
     * @return Response<UsuariosAPI>: Objeto que contiene la respuesta de la API
     **/
    @PUT("api/usuario/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: String,
        @Body usuario: UsuariosAPI
    ): Response<Void>

    /**
     * Manda una petición PUT a la API para actualizar ciertos datos del usuario
     * @param id_carrito: El id del carrito que deseas actualizar
     * @param id_producto_carrito: El id del producto que deseas actualizar la cantidad
     * @return Response<Vois>: No retorna nada
     **/
    @PUT("api/carrito/{id_carrito}/{id_producto_carrito}")
    suspend fun actualizarCantidad(
        @Path("id_carrito") id_carrito: String,
        @Path("id_producto_carrito") id_producto_carrito: String,
        @Body carrito: ActualizarCarritoAPI // Enviar el objeto CarritoAPI en el cuerpo
    ): Response<Void>

    /**
     * Manda una petición PUT a la API para actualizar ciertos datos del usuario
     * @param id_carrito: El id del carrito al que vas actualizar el estado
     * @return Response<Vois>: No retorna nada
     **/
    @PUT("api/carrito/{id_carrito}")
    suspend fun actualizarEstadoCarrito(
        @Path("id_carrito") id_carrito: String,
    ): Response<Void>
}