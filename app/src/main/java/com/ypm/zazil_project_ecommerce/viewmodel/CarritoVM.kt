package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.AddCarritoAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.CarritoAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarritoVM: ViewModel() {

    // Variable que corresponde al icono del carrito
    private val _iconoCarrito = MutableStateFlow(R.drawable.car_icon_outline)
    val iconoCarrito: StateFlow<Int> = _iconoCarrito

    private val _cantidadInicial = MutableStateFlow(1)
    val cantidadInicial: StateFlow<Int> = _cantidadInicial

    private var ultimaPresionIncrementar = System.currentTimeMillis()
    private var ultimaPresionDecrementar = System.currentTimeMillis()

    // Variable que corresponde al estado del carrito
    private val _estadoCarrito = MutableStateFlow("PENDIENTE")
    val estadoCarrito: StateFlow<String> = _estadoCarrito

    // Variable que corresponde a la lista de productos del carrito
    private val _listaCarrito = MutableStateFlow<List<CarritoAPI>>(emptyList())
    val listaCarrito: StateFlow<List<CarritoAPI>> = _listaCarrito

    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total

    val controlador = ControladorServicioAPI()
    private val _id_carrito = MutableLiveData<String>()
    val id_carrito = MutableStateFlow("")


    fun obtenerListaCarrito(id_usuario: String?){
        viewModelScope.launch {
            val lista = controlador.obtenerListaCarrito(id_usuario.toString())
            _listaCarrito.value = lista
        }
    }

    fun addCarrito(
        id_producto: Int,
        id_usuario: String?,
        cantidad: Int,
        navController: NavController
    ){
        viewModelScope.launch {
            try{
                _iconoCarrito.value = R.drawable.car_icon_fill
                val response = controlador.AddCarrito(id_usuario.toString(), id_producto.toString(), cantidad.toString())

                if(response.isSuccessful){
                    response.body()?.let {
                        Toast.makeText(navController.context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e: Exception){
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun borrarProductoCarrito(usuarioID: String?, id_producto: Int, navController: NavController){
        viewModelScope.launch {
            if(_listaCarrito.value.isEmpty()){
                val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                _listaCarrito.value = lista
                for (producto in _listaCarrito.value) {
                    if(producto.id_producto == id_producto){
                        val response = controlador.borrarProductoCarrito(producto.id_carrito.toString(), producto.id_producto_carrito.toString())
                        if(response.isSuccessful){
                            Toast.makeText(navController.context, "Producto eliminado del carrito", Toast.LENGTH_SHORT).show()
                            val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                            _listaCarrito.value = lista
                        }
                    }
                }
            }
        }
    }

    fun decrementarCantidad(usuarioID: String?, id_producto: Int, nuevaCantidad: Int, navController: NavController){
        if (_cantidadInicial.value > 1) {
            _cantidadInicial.value -= 1
            ultimaPresionDecrementar = System.currentTimeMillis()

            viewModelScope.launch {
                delay(2000) // Espera de 2 segundos
                if (System.currentTimeMillis() - ultimaPresionDecrementar >= 2000) {
                    if(_listaCarrito.value.isEmpty()){
                        val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                        _listaCarrito.value = lista
                        for (producto in _listaCarrito.value) {
                            if(producto.id_producto == id_producto){
                                val response = controlador.actualizarCantidad(producto.id_carrito.toString(), producto.id_producto_carrito.toString(), nuevaCantidad)
                                if(response.isSuccessful){
                                    Toast.makeText(navController.context, "Cantidad cambiada", Toast.LENGTH_SHORT).show()
                                    val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                                    _listaCarrito.value = lista
                                }
                            }
                        }
                    }
                }
            }
        }else{
            Toast.makeText(navController.context, "No se puede decrementar más", Toast.LENGTH_SHORT).show()
        }
    }

    fun incrementarCantidad(usuarioID: String?, id_producto: Int, nuevaCantidad: Int,stock: Int, navController: NavController){
        if (_cantidadInicial.value < stock) {
            _cantidadInicial.value += 1
            ultimaPresionIncrementar = System.currentTimeMillis()

            viewModelScope.launch {
                delay(2000) // Espera de 2 segundos
                if (System.currentTimeMillis() - ultimaPresionIncrementar >= 2000) {
                    if(_listaCarrito.value.isEmpty()){
                        val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                        _listaCarrito.value = lista
                        for (producto in _listaCarrito.value) {
                            if(producto.id_producto == id_producto){
                                val response = controlador.actualizarCantidad(producto.id_carrito.toString(), producto.id_producto_carrito.toString(), nuevaCantidad)
                                if(response.isSuccessful){
                                    Toast.makeText(navController.context, "Cantidad cambiada", Toast.LENGTH_SHORT).show()
                                    val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                                    _listaCarrito.value = lista
                                }
                            }
                        }
                    }
                }
            }
        }else{
            Toast.makeText(navController.context, "No hay más stock", Toast.LENGTH_SHORT).show()
        }
    }

    fun cambiarEstadoCarrito(usuarioID: String?, navController: NavController){
         viewModelScope.launch {
             if(_listaCarrito.value.isNotEmpty()){
                 val lista = controlador.obtenerListaCarrito(usuarioID.toString())
                 _listaCarrito.value = lista
                 _listaCarrito.value.forEach { carrito ->
                     val response = controlador.actualizarEstadoCarrito(carrito.id_carrito.toString())
                     if(response.isSuccessful){
                         Toast.makeText(navController.context, "Estado del carrito actualizado", Toast.LENGTH_SHORT).show()
                         controlador.carritoCompletado(usuarioID.toString())
                     }
                 }
             }
         }
    }

    fun obtenerTotal(listaProductos: List<CarritoAPI>){
        viewModelScope.launch {
            if(listaProductos.isNotEmpty()){
                for (producto in listaProductos) {
                    _total.value += (producto.precio * producto.cantidad)
                }
            }
        }
    }
}