package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ControladorServicioAPI {
    // Configuración de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.48.65.115:4000/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioGET by lazy {
        retrofit.create(ServicioGETAPI::class.java)
    }

    private val servicioPOST by lazy {
        retrofit.create(ServicioPOSTAPI::class.java)
    }

    // Método para obtener la lista de productos
    suspend fun obtenerListaProductos(): List<ProductosAPI> {
        return servicioGET.obtenerListaProductos()
    }

    suspend fun login(correo: String, password: String): Response<LoginResponse> {
        return servicioPOST.verificarUsuario(correo, password)
    }

    suspend fun register(nombre: String, apellidoPaterno: String, apellidoMaterno: String, correo: String, password: String): Response<UsuariosAPI> {
        return servicioPOST.registrarUsuario(nombre, apellidoPaterno, apellidoMaterno, correo, password)
    }
}