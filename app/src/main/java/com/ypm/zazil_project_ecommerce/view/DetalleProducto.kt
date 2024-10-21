package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.viewmodel.CarritoVM
import com.ypm.zazil_project_ecommerce.viewmodel.ProductoVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetalleUI(id: String?, navController: NavHostController, carritoVM: CarritoVM = CarritoVM()){
    val productoVM: ProductoVM = viewModel()
    val infoProducto = productoVM.producto.collectAsState().value

    id?.let {
        productoVM.obtenerProducto(it)
    }

    Scaffold(
        bottomBar = { BottomBar(id, navController = navController)}
    ){ innerPadding ->
        infoProducto.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ){
                Column(

                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(380.dp)
                                .customBackgrond(idColor = R.color.primary_500)
                        ){
                            Text(
                                text = it.nombre_producto,
                                color = Color.White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 36.dp, top = 16.dp, start = 16.dp, end = 16.dp),
                                maxLines = 2,
                                lineHeight = 32.sp
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Box(
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(210.dp)
                                        .background(Color.White)
                                        .border(shape = RoundedCornerShape(12.dp), width = 1.dp, color = Color.White)
                                ){
                                    AsyncImage(
                                        model = "http://187.145.186.58:4000/api/imagenes/" + it.ruta_img,
                                        contentDescription = "zail",
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter),

                            ){
                            FloatingActionButton(
                                containerColor = Color(0xFF5885C6),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .width(90.dp),
                                onClick = { /**/ },
                            ){
                                Text(
                                    text = "$" +  it.precio.toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }

                            FloatingActionButton(
                                containerColor = Color(0xFF5885C6),
                                modifier = Modifier
                                    .padding(16.dp),
                                onClick = {
                                    carritoVM.addCarrito(it.id_producto, id, 1, navController)
                                },
                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.car_icon_fill),
                                    contentDescription = "car",
                                    tint = Color.White,

                                    )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Stock: ${it.stock}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 5.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Descripción:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        Text(text = it.descripcion, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetalle(){
    val navController = rememberNavController()
    DetalleUI(id = "1", navController)
}
