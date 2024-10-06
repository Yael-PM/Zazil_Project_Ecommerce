package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.model.ProductosAPI
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
                .background(Color.White),  // Color rosa de fondo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Text(
                text = "Tienda",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            SearchBar()

            BannerCarrusel(bannerItems = banners)

            ProductoListScreen()
        }
    }
}

@Composable
fun ProductoListScreen(productoVM: ProductoVM = viewModel()) {

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

    LazyColumn(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
    ){
        productos.forEach { producto ->
            item {
                CardStore(
                    imagen = R.drawable.logo_zazil_prueba,
                    nombre = producto.nombre_producto,
                    precio = producto.precio.toString(),
                    rating = "5.0"
                )
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