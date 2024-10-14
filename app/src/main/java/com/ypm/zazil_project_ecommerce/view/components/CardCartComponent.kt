package com.ypm.zazil_project_ecommerce.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond

class CardCartComponent {

    // Debe de estar dentro de un lazy column con alineado al centro
    @Composable
    fun CardCart(
        id: Int,
        imagen: String,
        nombre: String,
        precio: String,
        stock: Int,
        onIncrementar: () -> Unit,
        onDecrementar: () -> Unit,
        onEliminar: () -> Unit
    ){
        // Tarjeta que contiene la información del producto
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(370.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen del producto
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.logo_zazil_prueba),
                        contentDescription = nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    // Nombre y precio del producto
                    Text(
                        text = nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                    )
                    Text(
                        text = "$${precio}",
                        fontSize =24.sp,
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(horizontalAlignment = Alignment.End) {
                    // Botón de eliminar
                    IconButton(
                        onClick = onEliminar,
                        modifier = Modifier
                            .background(Color(0xFFD22973), RoundedCornerShape(6.dp))
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_icon_outline),
                            contentDescription = "Eliminar",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Botones para aumentar y disminuir la cantidad
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onDecrementar,
                            modifier = Modifier
                                .background(Color(0xFF5885C6), RoundedCornerShape(6.dp))
                                .size(28.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(text = "-", fontSize = 20.sp, color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "${stock}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            onClick = onIncrementar,
                            modifier = Modifier
                                .background(Color(0xFF5885C6), RoundedCornerShape(6.dp))
                                .size(28.dp)
                        ) {
                            Text(text = "+", fontSize = 18.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
    
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun PreviewCardCart(){
        CardCart(
            id = 1,
            imagen = "https://picsum.photos/200",
            nombre = "Producto a comprar",
            precio = "100.00",
            stock = 2,
            onIncrementar = {},
            onDecrementar = {},
            onEliminar = {}
        )
    }
}

