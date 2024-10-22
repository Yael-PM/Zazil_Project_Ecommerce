package com.ypm.zazil_project_ecommerce.model

import com.ypm.zazil_project_ecommerce.model.dataAPI.ActualizarCarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.AddCarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.BannesrAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.CarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.HistorialAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginRequest
import com.ypm.zazil_project_ecommerce.model.dataAPI.LoginResponse
import com.ypm.zazil_project_ecommerce.model.dataAPI.PreguntasAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.RespuestasAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.UsuariosAPI
import okhttp3.OkHttpClient
import retrofit2.Call
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
            .baseUrl("http://189.139.200.234:4000/") // URL base de la API
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

    /**
     * Función que obtiene el carrito con la lista de productos agregados
     * @param id: String que contiene el id del usuario al que se le asocia ese carrito
     * @return carrito: Objeto que contiene la respuesta de la API
     **/
    suspend fun obtenerListaCarrito(id: String): List<CarritoAPI> {
        val carrito = servicioGET.obtenerCarrito(id)
        return carrito
    }

    /**
     * Función que obtiene la lista de banners para mostrar en la tienda
     * @return banners: Objeto que contiene la lista de banners
     **/
    suspend fun obtenerListaBanners(): List<BannesrAPI> {
        val banners = servicioGET.obtenerBanners()
        return banners
    }

    /**
     * Función que muestra al carrito como completado para poder pagar
     * @param id_usuario: String que contiene el id del usuario
     * @return carritoCompletado: Boolean que contiene el estado del carrito:
     **/
    suspend fun carritoCompletado(id_usuario: String): Boolean {
        val carritoCompletado = servicioGET.carritoCompletado(id_usuario)
        return carritoCompletado
    }

    /**
     * Obtiene una lista de respuestas de la API.
     * Esta función realiza una llamada a la API para recuperar una lista de respuestas.
     * @return Una lista de objetos `RespuestasAPI` que representan las respuestas obtenidas en el foro
     */
    suspend fun obtenerListaRespuestas(): List<RespuestasAPI> {
        return servicioGET.obtenerRespuesta()
    }

    /**
     * Obtiene una lista de historial de compras de la API.
     * Esta función realiza una llamada a la API para recuperar una lista de historial de compras.
     * @return Una lista de objetos `HistorialAPI` que representan el historial de compras
     */
    suspend fun obtenerHistorial(id_usuario: String): List<HistorialAPI> {
        return servicioGET.obtenerHistorial(id_usuario)
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
            tipo_usuario = tipoUsuario, // Valor por defecto
            estatus_usuario = estatus, // Valor por defecto
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
        cantidad: String
    ): Response<AddCarritoAPI> {
        val carrito = AddCarritoAPI(
            id_usuario = id_usuario,
            id_producto = id_producto,
            cantidad = cantidad
        )
        return servicioPOST.addCarrito(carrito)
    }

    /**
     * Función que agrega una pregunta al foro
     * @param id_usuario: String que contiene el id del usuario
     * @param titulo: String que contiene el titulo de la pregunta
     **/
    suspend fun addPregunta(
        id_usuario: String,
        titulo: String,
    ): Response<PreguntasAPI> {
        val pregunta = PreguntasAPI(
            id_usuario = id_usuario,
            titulo = titulo,
        )
        return servicioPOST.addPregunta(pregunta)
    }

    /**************************************************
     * SERVICIOS DELETE PARA BORRAR INFORMACIÓN DE LA API
     **************************************************/
    private val servicioDELETE by lazy {
        retrofit.create(ServicioDELETEAPI::class.java)
    }

    /**
     * Elimina un producto específico de un carrito de compras.
     * Esta función realiza una petición DELETE a la API para remover un producto del carrito identificado por su ID
     * @param id_carrito El identificador único del carrito de compras.
     * @param id_producto_carrito El identificador único del producto dentro del carrito.
     * @return Una respuesta de tipo `Response<Void>` que indica el resultado de la operación.
     */
    suspend fun borrarProductoCarrito(
        id_carrito: String,
        id_producto_carrito: String
    ): Response<Void> {
        return servicioDELETE.borrarProductoCarrito(id_carrito, id_producto_carrito)
    }

    /**************************************************
     * SERVICIOS PUT PARA OBTENER INFORMACIÓN DE LA API
     **************************************************/
    private val servicioPUT by lazy {
        retrofit.create(ServicioPUTAPI::class.java)
    }

    /**
     * Actualiza la cantidad de un producto en un carrito de compras.
     * Esta función realiza una petición PUT a la API para modificar la cantidad de un producto específico dentro de un carrito.
     * @param id_carrito El identificador único del carrito de compras.
     * @param id_producto_carrito El identificador único del producto dentro del carrito.
     * @param nuevaCantidad La nueva cantidad que se desea asignar al producto.
     * @return Una respuesta de tipo `Response<Void>` que indica el resultado de la operación.
     */
    suspend fun actualizarCantidad(
        id_carrito: String,
        id_producto_carrito: String,
        nuevaCantidad: Int // Pasar la nueva cantidad como parámetro
    ): Response<Void> {
        // Crear el objeto CarritoAPI con la nueva cantidad
        val carritoActualizado = ActualizarCarritoAPI(
            id_carrito = id_carrito,
            id_producto_carrito = id_producto_carrito,
            cantidad = nuevaCantidad // Asignar la nueva cantidad
        )

        // Llamar al servicio PUT
        return servicioPUT.actualizarCantidad(id_carrito, id_producto_carrito, carritoActualizado)
    }

    /**
     * Actualiza el estado de un carrito de compras.
     * Esta función realiza una petición PUT a la API para cambiar el estado de un carrito (por ejemplo, de "en proceso" a "completado").
     * @param id_carrito El identificador único del carrito de compras.
     * @return Una respuesta de tipo `Response<Void>` que indica el resultado de la operación.
     */
    suspend fun actualizarEstadoCarrito(
        id_carrito: String
    ): Response<Void>{
        return servicioPUT.actualizarEstadoCarrito(id_carrito)
    }

    /**
     * Actualiza la información de un usuario.
     * Esta función realiza una petición PUT a la API para modificar los datos de un usuario.
     * @param id_usuario El identificador único del usuario.
     * @param usuario Un objeto de tipo `UsuariosAPI` que contiene la nueva información del usuario.
     * @return Una respuesta de tipo `Response<Void>` que indica el resultado de la operación.
     */
    suspend fun actualizarUsuario(
        id_usuario: String,
        usuario: UsuariosAPI
    ): Response<Void>{
        val infoNuevaUsuario = servicioPUT.actualizarUsuario(id_usuario, usuario)
        return infoNuevaUsuario
    }
}