package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
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
            .baseUrl("http://187.145.186.58:4000/") // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * SERVICIOS GET PARA OBTENER INFORMACIÓN DE LA API
     **/
    private val servicioGET by lazy {
        retrofit.create(ServicioGETAPI::class.java)
    }

    // Método para obtener la lista de productos
    suspend fun obtenerListaProductos(): List<ProductosAPI> {
        return servicioGET.obtenerListaProductos()
    }

    //Método para obtener el detalle de un producto
    suspend fun obtenerProducto(id: String): ProductosAPI {
        val producto = servicioGET.obtenerProducto(id)
        return producto
    }

    //Método para obtener el perfil de un usuario
    suspend fun obtenerUsuario(id: String): LoginResponse {
        val usuario = servicioGET.obtenerUsuario(id)
        return usuario
    }

    /**
     * SERVICIOS POST PARA OBTENER INFORMACIÓN DE LA API
     **/
    private val servicioPOST by lazy {
        retrofit.create(ServicioPOSTAPI::class.java)
    }

    // Método para iniciar sesión con correo y contraseña con una petición POST
    suspend fun login(correo: String, password: String): Response<LoginResponse> {
        val usuario = LoginRequest(
            email = correo,
            password = password
        )
        return servicioPOST.verificarUsuario(usuario)
    }

    // Método para registrar un nuevo usuario con una petición POST
    suspend fun register(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        correo: String,
        password: String,
        tipoUsuario: String = "usuario_n",  // Valor predeterminado del usuario nomarl
        estatus: String = "activo"            // Valor predeterminado
    ): Response<UsuariosAPI> {
        // Crea un objeto UsuariosAPI con los datos proporcionados
        val usuario = UsuariosAPI(
            id_usuario = 999, // Se inicializa en 0 porque normalmente el servidor genera este ID
            nombre = nombre,
            apellido_paterno = apellidoPaterno,
            apellido_materno = apellidoMaterno,
            f_nacimiento = "10/10/2001",
            tipo_usuario = tipoUsuario, // Valor por defecto
            estatus = estatus, // Valor por defecto
            email = correo,
            ruta_img = "img", // Si no tienes imagen, puedes dejarlo vacío para luego modificarla en el perfil
            password = password
        )

        // Envía la petición POST con el objeto usuario
        return servicioPOST.registrarUsuario(usuario)
    }
}