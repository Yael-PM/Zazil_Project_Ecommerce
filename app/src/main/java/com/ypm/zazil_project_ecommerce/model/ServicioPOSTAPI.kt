package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.AddCarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.PreguntasAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicioPOSTAPI {
    /**
     * Manda una petición POST a la API para verificar que un usuario existe
     * @param usuario: Objeto que contiene el correo y la contraseña del usuario
     * @return Response<LoginResponse>: Objeto que contiene la respuesta de la API
     **/
    @POST("api/login") // Endpoint de la API para hacer un login
    suspend fun verificarUsuario(
        @Body usuario: LoginRequest
    ): Response<LoginResponse>

    /**
     * Manda una petición POST a la API para registrar a un usuario
     * @param usuario: Objeto que contiene la información de registro del usuario
     * @return Response<UsuariosAPI>: Objeto que contiene la respuesta de la API
     **/
    @POST("api/register") // Endpoint de la API para registrar un usuario
    suspend fun registrarUsuario(
        @Body usuario: UsuariosAPI
    ): Response<UsuariosAPI>

    /**
     * Manda una petición POST para crear un carrito y agregar un producto en el
     * @param carrito: Objeto que contiene datos de tipo AddCarritoAPI
     * @return Response<AddCarritoAPI>: Objeto que contiene la respuesta de la API
     **/
    @POST("api/carrito") // Endpoint de la API para agregar un producto al carrito
    suspend fun addCarrito(
        @Body carrito: AddCarritoAPI
    ): Response<AddCarritoAPI>

    @POST("api/preguntas")
    suspend fun addPregunta(
        @Body pregunta: PreguntasAPI
    ): Response<PreguntasAPI>
}