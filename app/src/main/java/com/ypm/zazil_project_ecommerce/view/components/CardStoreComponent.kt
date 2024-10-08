package com.ypm.zazil_project_ecommerce.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond

@SuppressLint("ResourceAsColor")
@Composable
fun CardStore(
    imagen: String,
    nombre: String,
    precio: String,
    rating: String
){
    Card(
      elevation = CardDefaults.cardElevation(5.dp)
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
                        model = R.drawable.logo_zazil_prueba,
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
                        fontWeight = FontWeight.Bold
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
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = rating,
                                color = Color.Black,
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.star_completa_icon),
                                contentDescription = "star",
                                tint = Color.Yellow,
                                modifier = Modifier
                                    .size(16.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(30.dp)
                            .border(
                                shape = RoundedCornerShape(5.dp),
                                width = 1.dp,
                                color = Color(R.color.primary_500)),
                        contentAlignment = Alignment.Center,
                    ){
                        Button(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),

                            onClick = { }
                        ){
                            Text(
                                text = "Comprar",
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCard(){
    CardStore(
        imagen = R.drawable.logo_zazil_prueba.toString(),
        nombre = "Producto",
        precio = "$100.00",
        rating = "4.5"
    )
}