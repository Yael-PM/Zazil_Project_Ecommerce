package com.ypm.zazil_project_ecommerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.Banners
import com.ypm.zazil_project_ecommerce.model.dataAPI.BannesrAPI
import com.ypm.zazil_project_ecommerce.model.dataAPI.ProductosAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar los productos y banners de la tienda
class ProductoVM : ViewModel() {

    // Controlador que realiza las peticiones a la API
    private val controlador = ControladorServicioAPI()

    // MutableStateFlow para la lista de productos, que puede ser observada por la UI
    private val _listaProductos = MutableStateFlow<List<ProductosAPI>>(emptyList())
    // StateFlow que expone la lista de productos de manera inmutable
    val listaProductos: StateFlow<List<ProductosAPI>> = _listaProductos

    // MutableStateFlow para la lista de banners
    private val _listaBanners = MutableStateFlow<List<BannesrAPI>>(emptyList())
    // StateFlow que expone la lista de banners de manera inmutable
    val listaBanners: StateFlow<List<BannesrAPI>> = _listaBanners

    // MutableStateFlow para un producto individual
    private val _producto = MutableStateFlow(ProductosAPI())
    // StateFlow que expone el producto individual de manera inmutable
    val producto: StateFlow<ProductosAPI> = _producto

    // Estado que indica si la descarga de productos está en curso
    private val _descargando = MutableStateFlow(false)
    // StateFlow que expone el estado de la descarga de manera inmutable
    val descargando: StateFlow<Boolean> = _descargando

    /**
     * Método para obtener la lista de productos desde la API y actualizar el estado.
     * Inicia la descarga cambiando el estado de `_descargando` a `true` y luego lo desactiva una vez que la lista de productos se obtiene.
     */
    fun obtenerListaProductos() {
        _descargando.value = true // Indica que la descarga ha comenzado
        viewModelScope.launch {
            // Realiza la llamada para descargar la lista de productos desde la API
            val listaP = controlador.obtenerListaProductos()
            _listaProductos.value = listaP // Actualiza la lista de productos
            _descargando.value = false // Finaliza la descarga
        }
    }

    /**
     * Método para obtener un producto específico por su ID desde la API.
     * Inicia la descarga cambiando el estado de `_descargando` a `true`.
     * @param id El identificador del producto a obtener.
     */
    fun obtenerProducto(id: String) {
        _descargando.value = true // Indica que la descarga ha comenzado
        viewModelScope.launch {
            _producto.value = controlador.obtenerProducto(id) // Actualiza el producto específico
        }
    }

    /**
     * Método para obtener la lista de banners desde la API.
     * No afecta el estado de descarga porque los banners pueden descargarse en segundo plano.
     */
    fun obtenerListaBanners() {
        viewModelScope.launch {
            _listaBanners.value = controlador.obtenerListaBanners() // Actualiza la lista de banners
        }
    }
}
