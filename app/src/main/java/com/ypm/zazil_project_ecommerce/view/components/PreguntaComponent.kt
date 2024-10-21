@file:Suppress("UNUSED_EXPRESSION")

package com.ypm.zazil_project_ecommerce.view.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("ResourceAsColor")
@Composable
fun CardPregunta(
    id_pregunta: String,
    id_usuario: String,
    firstName: String,
    lastName: String,
    titulo: String,
    id_usuario_respuesta: String,
    respuesta: String,
    fecha_respuesta: String
) {
    // Estado para manejar si la respuesta está expandida o colapsada
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFF5885C6),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(22.dp)
                .height(75.dp)
                .width(351.dp)
        ) {
            // Información del usuario que hizo la pregunta
            Text(
                text = "$firstName $lastName",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // Título de la pregunta
            Text(
                text = titulo,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 5.dp),
            )
        }

        // Mostrar la respuesta con AnimatedVisibility dependiendo del estado isExpanded
        AnimatedVisibility(visible = isExpanded) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color(0xFFBABEC7),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(22.dp)
                    .fillMaxWidth(),
            ) {
                // Respuesta a la pregunta
                Text(
                    text = respuesta,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 1.dp)
                )
                // Fecha de la respuesta
                Text(
                    text = fecha_respuesta,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
            }
        }

        // Botón para expandir o colapsar la respuesta
        FloatingActionButton(
            onClick = { isExpanded = !isExpanded },  // Cambia el estado de isExpanded
            containerColor = Color(0xFF4E73AA),
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .size(40.dp)
        ) {
            // Cambia el ícono dependiendo del estado
            Text(if (isExpanded) "-" else "+", fontSize = 24.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCardPregunta() {
    var showAnswer by remember { mutableStateOf(false) }
    CardPregunta(
        id_pregunta = "700",
        id_usuario = "1000",
        firstName = "Ximena",
        lastName = "Dorantes",
        titulo = "Pregunta 1. ¿Qué toalla debería usar si mi flujo menstrual es abundante?",
        id_usuario_respuesta = "800",
        respuesta = "Respuesta 1. Buenas tardes, podrías usar las toallas nocturnas...",
        fecha_respuesta = "2024-10-14",
    )
}