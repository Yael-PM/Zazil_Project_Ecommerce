package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServicioPOSTAPI {
    /**
     * Manda una petición POST a la API para verificar que un usuario existe
     **/
    @POST("api/login")
    suspend fun verificarUsuario(
        @Body usuario: LoginRequest
    ): Response<LoginResponse>

    @POST("api/register")
    suspend fun registrarUsuario(
        @Body usuario: UsuariosAPI
    ): Response<UsuariosAPI>
}