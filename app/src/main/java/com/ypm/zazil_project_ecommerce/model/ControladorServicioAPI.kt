package com.ypm.zazil_project_ecommerce.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ControladorServicioAPI {
    // Configuración de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.72.26:4000/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicio by lazy {
        retrofit.create(ServicioAPI::class.java)
    }

    // Método para obtener la lista de productos
    suspend fun obtenerListaProductos(): List<ProductosAPI> {
        return servicio.obtenerListaProductos()
    }

}