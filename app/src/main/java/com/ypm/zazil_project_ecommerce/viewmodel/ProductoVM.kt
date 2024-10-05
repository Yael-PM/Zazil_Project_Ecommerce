package com.ypm.zazil_project_ecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ypm.zazil_project_ecommerce.model.ControladorServicioAPI
import com.ypm.zazil_project_ecommerce.model.ProductosAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoVM: ViewModel() {

    private val repositorio = ControladorServicioAPI()

    private val _productos = MutableStateFlow<List<ProductosAPI>>(emptyList())
    val productos: StateFlow<List<ProductosAPI>> = _productos

    // Método para obtener los productos y actualizar el estado
    fun obtenerProductos() {
        viewModelScope.launch {
            val productosList = repositorio.obtenerProductos()
            if (productosList != null) {
                _productos.value = productosList

                // Imprimir la lista de productos
                Log.d("ProductoViewModel", "Lista de productos: $productosList")
            } else {
                Log.d("ProductoViewModel", "No se pudieron obtener los productos.")
            }
        }
    }
}