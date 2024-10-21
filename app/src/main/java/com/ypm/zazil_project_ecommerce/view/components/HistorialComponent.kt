package com.ypm.zazil_project_ecommerce.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HistorialCard(
    id: String,
    estado: String,
    total: String,
    fecha: String
){
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = id, overflow = TextOverflow.Ellipsis, color = Color.Black)
            Text(text = total, overflow = TextOverflow.Ellipsis, color = Color.Black)
            Text(text = fecha, overflow = TextOverflow.Ellipsis, color = Color.Black, modifier = Modifier.width(100.dp))
            Text(text = estado, overflow = TextOverflow.Ellipsis, color = Color.Black)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHistorialCard() {
    HistorialCard(
        id = "1",
        estado = "Recibido",
        total = "$132.89",
        fecha = "07/08/23"
    )
}