package com.ypm.zazil_project_ecommerce.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ypm.zazil_project_ecommerce.viewmodel.ProductoVM

@Composable
fun BannerCarrusel(productoVM: ProductoVM = ProductoVM()) {

    val listaBanners by productoVM.listaBanners.collectAsState()

    // Efecto para el scroll automático
    LaunchedEffect(Unit) {
        productoVM.obtenerListaBanners()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(listaBanners) { item ->
                BannerItem(imageRes = item.ruta_banner)
            }
        }
    }
}

@Composable
fun BannerItem(imageRes: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(300.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(androidx.compose.ui.graphics.Color.Gray),
        shape = RoundedCornerShape(16.dp),
    ) {
        AsyncImage(
            model = "http://189.139.200.234:4000/api/banners/" + imageRes,
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxSize()
        )
    }
}