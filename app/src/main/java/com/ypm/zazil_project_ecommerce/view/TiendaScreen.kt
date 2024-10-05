package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
fun ProductoListScreen(viewModel: ProductoVM = viewModel()) {
    val productos by viewModel.productos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerProductos()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(productos.size) { index ->
            ProductoItem(producto = productos[index])
        }
    }
}

@Composable
fun ProductoItem(producto: ProductosAPI) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = producto.product_photo),
            contentDescription = producto.product_title,
            modifier = Modifier.size(80.dp)
        )

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = producto.product_title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = producto.product_price, fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
fun PreviewTienda(){
    val navController = rememberNavController()
    TiendaUI(navController)
}