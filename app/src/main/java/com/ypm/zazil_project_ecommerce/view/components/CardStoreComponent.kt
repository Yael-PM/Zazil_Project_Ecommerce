package com.ypm.zazil_project_ecommerce.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond
import com.ypm.zazil_project_ecommerce.viewmodel.CarritoVM

@SuppressLint("ResourceAsColor")
@Composable
fun CardStore(
    id: Int,
    imagen: String,
    nombre: String,
    precio: String,
    rating: Float,
    descripcion: String,
    stock: Int,
    navController: NavController
){


    Card(

    ){
        Box(
            modifier = Modifier
                .customBackgrond(idColor = R.color.white)
                .width(180.dp)
                .height(240.dp)
                .border(shape = RoundedCornerShape(12.dp), width = 1.dp, color = Color.White),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                ){
                    AsyncImage(
                        model = imagen,
                        contentDescription = "Toalla Ana",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 5.dp, top = 10.dp)
                ){
                    Text(
                        text = nombre,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("detalle/${id}")
                            }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = precio,
                            color = Color.Black,
                            fontSize = 16.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = rating.toString(),
                                color = Color.Black,
                                fontSize = 16.sp
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.star_completa_icon),
                                contentDescription = "star",
                                tint = Color.Yellow,
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFD22973),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .height(30.dp)
                            .clickable {
                                //Función que jala los elementos a la pantalla de pago

                            },
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Comprar",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // FloatingActionButton en la esquina superior derecha
            FloatingActionButton(
                onClick = {
                    //controladorCarrito(id, nombre, precio, stock, imagen)
                },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = 10.dp), // Ajusta la posición según el diseño
                containerColor = Color.Gray, // Color del FAB, por ejemplo rojo para eliminar
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.car_icon_fill), // Icono para el botón
                    contentDescription = "añadir a carrito"
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCard(){
    val navController = rememberNavController()
    CardStore(
        id = 1,
        imagen = R.drawable.logo_zazil_prueba.toString(),
        nombre = "Producto a comporar",
        precio = "$100.00",
        rating = 4.5F,
        descripcion = "Descripcion del producto",
        stock = 2,
        navController = navController
    )
}