package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.AddCarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

fun getUnsafeOkHttpClient(): OkHttpClient {
    try {
        // Crea un TrustManager que acepte todos los certificados
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Crea un contexto SSL con nuestro TrustManager que acepta todos los certificados
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        // Construye un OkHttpClient que acepta todos los certificados
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }

        return builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

class ControladorServicioAPI {
    val cliente = getUnsafeOkHttpClient()

    // Configuración de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://187.145.186.58:4000/") // URL base de la API
            //.client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**************************************************
     * SERVICIOS GET PARA OBTENER INFORMACIÓN DE LA API
     **************************************************/
    private val servicioGET by lazy {
        retrofit.create(ServicioGETAPI::class.java)
    }

    /**
     * Obtiene la lista de productos con una petición a la API
     * @return lista de productos: List<ProductosAPI>
     **/
    suspend fun obtenerListaProductos(): List<ProductosAPI> {
        return servicioGET.obtenerListaProductos()
    }

    /**
     * Obtiene la infomación de un solo producto
     * @param id: String que contiene el id del producto
     * @return producto: ProductosAPI que contiene la información del producto
     * */
    suspend fun obtenerProducto(id: String): ProductosAPI {
        val producto = servicioGET.obtenerProducto(id)
        return producto
    }

    /**
     * Obtiene la infomación de un usuario para mostrarse en home y perfil
     * @param id: String que contiene el id del usuario
     * @return usuario: UsuariosAPI que contiene la información del usuario
     * */
    suspend fun obtenerUsuario(id: String): UsuariosAPI {
        val usuario = servicioGET.obtenerUsuario(id)
        return usuario
    }

    /***************************************************
     * SERVICIOS POST PARA OBTENER INFORMACIÓN DE LA API
     ***************************************************/
    private val servicioPOST by lazy {
        retrofit.create(ServicioPOSTAPI::class.java)
    }

    /**
     * Función que verifica si el usuario esta registradito en la API
     * @param correo: String que contiene el correo del usuario
     * @param password: String que contiene la contraseña del usuario
     * @return Response<LoginResponse>: Objeto que contiene la respuesta de la API
     * */
    suspend fun login(correo: String, password: String): Response<LoginResponse> {
        val usuario = LoginRequest(
            email = correo,
            password = password
        )
        return servicioPOST.verificarUsuario(usuario)
    }

    /**
     * Función que registra un usuario en la API
     * @param nombre: String que contiene el nombre del usuario
     * @param apellidoPaterno: String que contiene el apellido paterno del usuario
     * @param apellidoMaterno: String que contiene el apellido materno del usuario
     * @param correo: String que contiene el correo del usuario
     * @param password: String que contiene la contraseña del usuario
     * @param tipoUsuario: String que contiene el tipo de usuario
     * @param estatus: String que contiene el estatus del usuario
     * @return Response<UsuariosAPI>: Objeto que contiene la respuesta de la API
     * */
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

    /**
     * Función que agrega un producto al carrito y por lo tanto lo crea en la BD
     * @param id_usuario: String que contiene el id del usuario
     * @param id_producto: String que contiene el id del producto
     * @param cantidad: Int que contiene la cantidad del producto
     * @return Response<AddCarrito>: Objeto que contiene la respuesta de la API
     * */
    suspend fun AddCarrito(
        id_usuario: String,
        id_producto: String,
        cantidad: Int
    ): Response<AddCarritoAPI> {
        val carrito = AddCarritoAPI(
            id_usuario = 999,
            id_producto = 999,
            cantidad = 999
        )
        return servicioPOST.addCarrito(carrito)
    }
}