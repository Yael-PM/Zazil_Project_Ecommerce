package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServicioPUTAPI {

    /**
     * Manda una petición PUT a la API para actualizar ciertos datos
     **/
    @PUT("api/updateUsuario/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: Int,               // El id del usuario que deseas actualizar
        @Body usuario: LoginResponse        // El objeto con los datos a actualizar
    ): Response<LoginResponse>
}