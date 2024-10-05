package com.ypm.zazil_project_ecommerce.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ControladorServicioAPI {
    // Configuración de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://real-time-amazon-data.p.rapidapi.com/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService = retrofit.create(ServicioAPI::class.java)

    //Método para obtener los productos
    suspend fun obtenerProductos(): List<ProductosAPI>? {
        return try{
            val response = apiService.obtenerProductosAPI(
                apiKey = "37755b6878msh16da0ba90e07d03p1826a1jsn808289c33a27",
                apiHost = "real-time-amazon-data.p.rapidapi.com"
            )

            if (response.isSuccessful) {
                response.body()?.products
            } else {
                null
            }
        } catch(e: Exception){
            null
        }
    }
}