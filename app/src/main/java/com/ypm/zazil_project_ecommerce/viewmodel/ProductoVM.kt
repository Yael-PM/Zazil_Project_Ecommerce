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

class ProductoVM: ViewModel() {

    private val controlador = ControladorServicioAPI()

    private val _listaProductos = MutableStateFlow<List<ProductosAPI>>(emptyList())
    val listaProductos: StateFlow<List<ProductosAPI>> = _listaProductos

    private val _listaBanners = MutableStateFlow<List<BannesrAPI>>(emptyList())
    val listaBanners: StateFlow<List<BannesrAPI>> = _listaBanners

    private val _producto = MutableStateFlow(ProductosAPI())
    val producto: StateFlow<ProductosAPI> = _producto

    // Estado de la descarga de productos
    private val _descargando = MutableStateFlow(false)
    val descargando: StateFlow<Boolean> = _descargando

    // Método para obtener la lista productos y actualizar el estado
    fun obtenerListaProductos() {
        _descargando.value = true
        viewModelScope.launch {
            //Descargar la lista de productos
            val listaP = controlador.obtenerListaProductos()
            _listaProductos.value = listaP
            _descargando.value = false
        }
    }

    fun obtenerProducto(id: String) {
        _descargando.value = true
        viewModelScope.launch {
            _producto.value = controlador.obtenerProducto(id)
        }
    }

    fun obtenerListaBanners() {
        viewModelScope.launch {
            _listaBanners.value = controlador.obtenerListaBanners()
        }
    }
}