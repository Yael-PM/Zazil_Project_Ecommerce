package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond
import com.ypm.zazil_project_ecommerce.view.components.BannerCarrusel
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.view.components.CardStore
import com.ypm.zazil_project_ecommerce.view.components.SearchBar
import com.ypm.zazil_project_ecommerce.viewmodel.ProductoVM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TiendaUI(navController: NavController){

    val banners = listOf(
        R.drawable.logo_zazil_prueba,
        R.drawable.star_completa_icon,
        R.drawable.star_half_icon
    )

    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ){ innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .customBackgrond(idColor = R.color.fondo_default)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ){
            Text(
                text = "Tienda",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            SearchBar()

            BannerCarrusel(bannerItems = banners)

            ProductoListScreen(navController = navController)
        }
    }
}

@Composable
fun ProductoListScreen(productoVM: ProductoVM = viewModel(), navController: NavController) {

    val productos by productoVM.listaProductos.collectAsState()
    val descargando by productoVM.descargando.collectAsState()

    LaunchedEffect(Unit) {
        productoVM.obtenerListaProductos()
    }

    // Indicador de descarga (círculo)
    if(descargando){
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 30.dp)
        )
    }

    Spacer(modifier = Modifier.height(30.dp))

    Box(
        modifier = Modifier
            .heightIn(min = 200.dp, max = 800.dp) // Controlar el tamaño máximo del grid
            .fillMaxWidth()
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            productos.forEach { producto ->
                item {
                    CardStore(
                        // La imagen está en proceso de mostrarse
                        id = producto.id_producto,
                        imagen = "http://187.145.186.58:4000/api/imagenes/" + producto.ruta_img,
                        nombre = producto.nombre_producto,
                        precio = producto.precio.toString(),
                        rating = producto.rating,
                        stock = producto.stock,
                        descripcion = producto.descripcion,
                        navController = navController
                    )
                    println(producto.ruta_img)
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewTienda(){
    val navController = rememberNavController()
    TiendaUI(navController)
}