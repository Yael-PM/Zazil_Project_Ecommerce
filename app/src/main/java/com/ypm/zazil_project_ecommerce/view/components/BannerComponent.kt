package com.ypm.zazil_project_ecommerce.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BannerCarrusel(
    bannerItems: List<Int>,  // Lista de imágenes (recursos drawables)
    autoScrollDelay: Long = 3000L // Tiempo de scroll automático en milisegundos
) {
    var currentIndex by remember { mutableStateOf(0) }

    // Efecto para el scroll automático
    LaunchedEffect(currentIndex) {
        delay(autoScrollDelay)
        currentIndex = (currentIndex + 1) % bannerItems.size // Avanza al siguiente, vuelve al inicio
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
            items(bannerItems) { item ->
                BannerItem(imageRes = item)
            }
        }
    }
}

@Composable
fun BannerItem(imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(300.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(androidx.compose.ui.graphics.Color.Gray),
        shape = RoundedCornerShape(16.dp),
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxSize()
        )
    }
}